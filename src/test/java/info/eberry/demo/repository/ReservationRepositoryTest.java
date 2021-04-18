package info.eberry.demo.repository;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.dto.FailedTransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void getAllFailedReservations() {
        Flux<Reservation> allFailedReservations = reservationRepository.findAllBySuccessful(false);

//flux has at least one element
        StepVerifier.create(allFailedReservations)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .expectRecordedMatches(elements -> elements.size() >= 2)
                .verifyComplete();

    }

    @Test
    public void getAllFailedReservationsDtos() {
        Flux<FailedTransactionDto> allFailedReservations = reservationRepository.findAllFailedReservations();

        //flux has at least one element
        StepVerifier.create(allFailedReservations)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .expectRecordedMatches(elements -> elements.size() >= 2)
                .verifyComplete();

    }

    @Test
    public void getAllSuccessfulReservations() {
        Flux<Reservation> allFailedReservations = reservationRepository.findAllBySuccessful(true);

        //flux has at least two element
        StepVerifier.create(allFailedReservations)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .expectRecordedMatches(elements -> elements.size() >= 1)
                .verifyComplete();
    }

    @Test
    public void sumAllSuccessfulCosts() {
        Mono<Long> costs = reservationRepository.sumAllSuccessfulCosts(3L);
        costs.doOnNext(sum -> {
            log.info(sum + "");
            assertEquals(sum, 200);
        }).subscribe();

    }

    @Test
    public void sumAllSuccessfulCostsIncorrect() {
        Mono<Long> costs = reservationRepository.sumAllSuccessfulCosts(3000L);
        StepVerifier.create(costs.log("Receiving values !!!"))
                .expectNextCount(0).verifyComplete();
    }
}