package info.eberry.demo.domain.service;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import info.eberry.demo.domain.repository.ReservationRepository;
import info.eberry.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    private BiFunction<User, Reservation, FailedTransactionDto> userReservationDTOBiFunction = (x1, x2) -> FailedTransactionDto.builder()
            .name(x1.getName())
            .transactionNumber(x2.getTransactionNumber())
            .family(x1.getFamily())
            .email(x1.getEmail()).build();
}
