package info.eberry.demo.domain.service;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.repository.ReservationRepository;
import info.eberry.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServiceImpl implements BookingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public void saveUser(User user) {

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
    public Mono<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
