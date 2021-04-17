package info.eberry.demo.domain.service;

import info.eberry.demo.domain.exception.DemoException;
import info.eberry.demo.domain.model.InitialCreditLimit;
import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import info.eberry.demo.domain.model.dto.ReservationDto;
import info.eberry.demo.domain.repository.InitialCreditLimitRepository;
import info.eberry.demo.domain.repository.ReservationRepository;
import info.eberry.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.BiFunction;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServiceImpl implements BookingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private InitialCreditLimitRepository limitRepository;

    @Override
    @Transactional
    public Mono<User> saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<User> getUserWithEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Flux<Reservation> getAllFailedBookings() {
        return reservationRepository.findAllBySuccessful(false);
    }

    @Override
    public Flux<FailedTransactionDto> getAllFailedBookingsDtos() {
        return reservationRepository.findAllFailedReservations();
    }

    @Override
    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id);
    }



    @Override
    @Transactional
    public Mono<Reservation> saveReservation(ReservationDto dto) {
        User user  = userRepository.findByEmail(dto.getEmail()).block();
        if(user == null)
            throw new DemoException();
        Mono<InitialCreditLimit> limit = limitRepository.findByUserId(user.getId()).subscribeOn(Schedulers.elastic());
        Mono<Long> sum = reservationRepository.sumAllSuccessfulCosts(user.getId()).defaultIfEmpty(0L).subscribeOn(Schedulers.elastic());

        BiFunction<Long, InitialCreditLimit, Reservation> userDepartmentDTOBiFunction = (s, l) ->{
            Reservation reservation = new Reservation();
            reservation.setCost(dto.getCost());
            reservation.setTransactionNumber(dto.getTransactionNumber());
            reservation.setUserId(user.getId());
            if(s+dto.getCost() > l.getAmount()){
                reservation.setSuccessful(false);
            }else {
                reservation.setSuccessful(true);
            }
            return reservation;
        };
        Reservation newReservation = Mono.zip(sum, limit, userDepartmentDTOBiFunction).block();
        return reservationRepository.save(newReservation);

      /*  Mono<User> user = userRepository.findByEmail(dto.getEmail());




        userRepository.findByEmail(dto.getEmail()).doOnNext(u -> {
            limitRepository.findByUserId(u.getId()).doOnNext(limit -> {
                reservationRepository.sumAllSuccessfulCosts(u.getId()).doOnNext(sum -> {

                    reservationRepository.save(reservation);
                }).subscribe();
            }).subscribe();
        }).subscribe();*/
    }

    private BiFunction<User, Reservation, FailedTransactionDto> userReservationDTOBiFunction = (x1, x2) -> FailedTransactionDto.builder()
            .name(x1.getName())
            .transactionNumber(x2.getTransactionNumber())
            .family(x1.getFamily())
            .email(x1.getEmail()).build();
}
