package info.eberry.demo.domain.service;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {
    void saveUser(User user);
    Mono<User> getUserWithEmail(String email);
    Flux<Reservation> getAllFailedBookings();

    Mono<User> getUserById(Long id);
}
