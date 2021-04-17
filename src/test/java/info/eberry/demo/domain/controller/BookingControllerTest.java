package info.eberry.demo.domain.controller;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import info.eberry.demo.domain.model.dto.ResponseContainerDto;
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
@AutoConfigureWebTestClient(timeout = "10000")
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
    /*@Test
    void getAllResponseContainerDto() {
        Flux<ResponseContainerDto> responseBody = webTestClient.get().uri("/resources/bookings/failed/general").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(ResponseContainerDto.class)
                .getResponseBody();
        StepVerifier.create(responseBody.log("Receiving values !!!"))
                .expectNextCount(1)
                .verifyComplete();
    }*/


    @Test
    void saveReservation() {
        Flux<Reservation> responseBody = webTestClient.post()
                .uri("/resources/bookings")
                .contentType(MediaType.valueOf(MediaType.TEXT_PLAIN_VALUE))
                .body(Mono.just("Amir,Azimi,amir.azimi.alasti@gmail.com,120,TR0005"), String.class)

                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .returnResult(Reservation.class)
                .getResponseBody();
        StepVerifier.create(responseBody.log("Receiving values !!!"))
                .expectNextCount(1)
                .verifyComplete();
    }
}