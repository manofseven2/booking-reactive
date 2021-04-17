package info.eberry.demo.domain.controller;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWebTestClient
@Slf4j
class BookingControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllFailedReservations() {
        Flux<Reservation> userFlux = webTestClient.get().uri("/resources/bookings/failed/values").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(Reservation.class)
                .getResponseBody();
        StepVerifier.create(userFlux.log("Receiving values !!!"))
                .expectNextCount(2)
                .verifyComplete();
    }
    @Test
    void getAllFailedBookingsDtos() {
        Flux<FailedTransactionDto> responseBody = webTestClient.get().uri("/resources/bookings/failed/dtos").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(FailedTransactionDto.class)
                .getResponseBody();
        StepVerifier.create(responseBody.log("Receiving values !!!"))
                .expectNextCount(2)
                .verifyComplete();
    }
}