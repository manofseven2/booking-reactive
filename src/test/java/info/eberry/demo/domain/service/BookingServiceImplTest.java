package info.eberry.demo.domain.service;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.dto.ReservationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
public class BookingServiceImplTest {

    @Autowired
    private BookingService bookingService;
    @Test
    void saveReservation() {
        ReservationDto dto = new ReservationDto("Amir", "Azimi", "amir.azimi.alasti@gmail.com", 300L, "TR0004");
        Mono<Reservation> reservationMono = bookingService.saveReservation(dto);
/*
        StepVerifier.create(reservationMono.log("stars: ")).assertNext(r ->
            assertNotNull(r.getUserId())
        ).verifyComplete();
*/
        StepVerifier.create(reservationMono.log("stars: "))
                .thenConsumeWhile(v -> v.getUserId()!=null)
        .verifyComplete();
    }
}