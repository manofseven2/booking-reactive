package info.eberry.demo.service;

import info.eberry.demo.exception.DemoException;
import info.eberry.demo.domain.model.InitialCreditLimit;
import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.dto.FailedTransactionDto;
import info.eberry.demo.domain.dto.ReservationDto;
import info.eberry.demo.repository.InitialCreditLimitRepository;
import info.eberry.demo.repository.ReservationRepository;
import info.eberry.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Flux<Reservation> saveReservation(ReservationDto dto) {
//        User user  = userRepository.findByEmail(dto.getEmail()).block();
        Mono<User> userMono = userRepository.findByEmail(dto.getEmail());
        Flux<Reservation> reservationFlux = userMono.repeat(0).flatMap(p -> Flux.zip(reservationRepository.sumAllSuccessfulCosts(p.getId()).defaultIfEmpty(0L),
                limitRepository.findByUserId(p.getId()),
                (s, l) -> {
                    Reservation reservation = new Reservation();
                    reservation.setCost(dto.getCost());
                    reservation.setTransactionNumber(dto.getTransactionNumber());
                    reservation.setUserId(p.getId());
                    if (s + dto.getCost() > l.getAmount()) {
                        reservation.setSuccessful(false);
                    } else {
                        reservation.setSuccessful(true);
                    }

                    return reservation;
                }));
        return reservationFlux.flatMap(r -> reservationRepository.save(r));
        //return null;

//        Mono<Flux<Mono<Reservation>>> map = userMono.map(p -> Flux.zip(reservationRepository.sumAllSuccessfulCosts(p.getId()).defaultIfEmpty(0L),
//                limitRepository.findByUserId(p.getId()),
//                (s, l) -> {
//                    Reservation reservation = new Reservation();
//                    reservation.setCost(dto.getCost());
//                    reservation.setTransactionNumber(dto.getTransactionNumber());
//                    reservation.setUserId(p.getId());
//                    if (s + dto.getCost() > l.getAmount()) {
//                        reservation.setSuccessful(false);
//                    } else {
//                        reservation.setSuccessful(true);
//                    }
//                    return reservationRepository.save(reservation);
//                    //return reservation;
//                }));
//        return null;

/*        userMono.flatMap(p -> {
            return Flux.zip(reservationRepository.sumAllSuccessfulCosts(p.getId()).defaultIfEmpty(0L),
                    limitRepository.findByUserId(p.getId()),
                    (s, l) -> {
                        Reservation reservation = new Reservation();
                        reservation.setCost(dto.getCost());
                        reservation.setTransactionNumber(dto.getTransactionNumber());
                        reservation.setUserId(p.getId());
                        if (s + dto.getCost() > l.getAmount()) {
                            reservation.setSuccessful(false);
                        } else {
                            reservation.setSuccessful(true);
                        }
                        return reservation;
                    });
        });*/

/*
        Mono<InitialCreditLimit> limit = limitRepository.findByUserId(user.getId()).subscribeOn(Schedulers.elastic());
        Mono<Long> sum = reservationRepository.sumAllSuccessfulCosts(user.getId()).defaultIfEmpty(0L).subscribeOn(Schedulers.elastic());

        BiFunction<Long, InitialCreditLimit, Reservation> sumAndLimitToReservation = (s, l) ->{
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
        Reservation newReservation = Mono.zip(sum, limit, sumAndLimitToReservation).block();
        return reservationRepository.save(newReservation);
*/

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
