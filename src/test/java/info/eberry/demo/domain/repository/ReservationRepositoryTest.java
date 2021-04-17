package info.eberry.demo.domain.repository;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void getAllFailedReservations(){
        Flux<Reservation> allFailedReservations = reservationRepository.findAllBySuccessful(false);

        StepVerifier.create(allFailedReservations.log("Receiving values !!!"))
                .expectNextCount(2)
                .verifyComplete();

    }
    @Test
    public void getAllFailedReservationsDtos(){
        Flux<FailedTransactionDto> allFailedReservations = reservationRepository.findAllFailedReservations();

        StepVerifier.create(allFailedReservations.log("Receiving values !!!"))
                .expectNextCount(2)
                .verifyComplete();

    }
    @Test
    public void getAllSuccessfulReservations(){
        Flux<Reservation> allFailedReservations = reservationRepository.findAllBySuccessful(true);

        StepVerifier.create(allFailedReservations.log("Receiving values !!!"))
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    public void sumAllSuccessfulCosts(){
        Mono<Long> costs = reservationRepository.sumAllSuccessfulCosts(3L);
        costs.doOnNext(sum -> {
            log.info(sum+"");
            assertEquals(sum, 200);
        }).subscribe();

    }
    @Test
    public void sumAllSuccessfulCostsIncorrect(){
        Mono<Long> costs = reservationRepository.sumAllSuccessfulCosts(3000L);
        StepVerifier.create(costs.log("Receiving values !!!"))
        .expectNextCount(0).verifyComplete();
    }
}