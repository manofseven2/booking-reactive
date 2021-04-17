package info.eberry.demo.domain.repository;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

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
}