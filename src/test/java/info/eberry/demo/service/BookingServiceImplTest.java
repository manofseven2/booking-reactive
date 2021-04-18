package info.eberry.demo.service;

import info.eberry.demo.domain.dto.FailedTransactionDto;
import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.dto.ReservationDto;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.exception.DemoException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
public class BookingServiceImplTest {

    @Autowired
    private BookingService bookingService;

    @Test
    void saveReservation() {
        ReservationDto dto = new ReservationDto("Amir", "Azimi", "amir.azimi.alasti@gmail.com", 300L, "TR0004");
        Flux<Reservation> reservationMono = bookingService.saveReservation(dto);
        StepVerifier.create(reservationMono.log("stars: "))
                .thenConsumeWhile(v -> v.getUserId() != null && !v.getSuccessful())
                .verifyComplete();
    }

    @Test
    public void getUserWithEmail() {
        Mono<User> user = bookingService.getUserWithEmail("kjhkjdfhkfkd");
        StepVerifier.create(user.log("getUserWithEmail: "))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void getUserWithEmailExistEmail() {
        User user = new User(1L, "Amir", "Azimi", "amir.azimi.alasti@gmail.com");
        Mono<User> userFlux = bookingService.getUserWithEmail("amir.azimi.alasti@gmail.com");
        StepVerifier.create(userFlux.log("Receiving values !!!"))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    public void getAllFailedBookings() {
        Flux<Reservation> allFailedReservations = bookingService.getAllFailedBookings();

        //flux has at least one element
        StepVerifier.create(allFailedReservations)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .expectRecordedMatches(elements -> elements.size() >= 2)
                .verifyComplete();
    }

    @Test
    public void getAllFailedReservationsDtos() {
        Flux<FailedTransactionDto> allFailedReservations = bookingService.getAllFailedBookingsDtos();

        //flux has at least one element
        StepVerifier.create(allFailedReservations)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .expectRecordedMatches(elements -> elements.size() >= 2)
                .verifyComplete();
    }
}