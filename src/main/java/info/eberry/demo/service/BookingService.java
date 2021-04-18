package info.eberry.demo.service;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.dto.FailedTransactionDto;
import info.eberry.demo.domain.dto.ReservationDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {
    Mono<User> saveUser(User user);
    Mono<User> getUserWithEmail(String email);
    Flux<Reservation> getAllFailedBookings();
    Flux<FailedTransactionDto> getAllFailedBookingsDtos();
    Flux<Reservation> saveReservation(ReservationDto dto);
}
