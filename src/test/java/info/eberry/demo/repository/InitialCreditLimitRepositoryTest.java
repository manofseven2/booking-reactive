package info.eberry.demo.repository;

import info.eberry.demo.domain.model.InitialCreditLimit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class InitialCreditLimitRepositoryTest {

    @Autowired
    private InitialCreditLimitRepository limitRepository;

    @Test
    public void getInitialCreditLimit() {
        Mono<InitialCreditLimit> allFailedReservations = limitRepository.findByUserId(1L);
        StepVerifier.create(allFailedReservations.log("Receiving values !!!"))
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    public void getInitialCreditLimitEmpty() {
        Mono<InitialCreditLimit> allFailedReservations = limitRepository.findByUserId(10000L);
        StepVerifier.create(allFailedReservations.log("Receiving values !!!"))
                .expectNextCount(0)
                .verifyComplete();

    }
}