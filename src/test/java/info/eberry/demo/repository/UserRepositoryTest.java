package info.eberry.demo.repository;

import info.eberry.demo.domain.model.InitialCreditLimit;
import info.eberry.demo.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InitialCreditLimitRepository limitRepository;

    @Test
    public void findByEmail() {
        Mono<User> user = userRepository.findByEmail("kjhkjdfhkfkd");
        StepVerifier.create(user)
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }

    @Test
    public void findByEmailExistEmail() {
        User user = new User(1L, "Amir", "Azimi", "amir.azimi.alasti@gmail.com");
        Mono<User> userFlux = userRepository.findByEmail("amir.azimi.alasti@gmail.com");
        StepVerifier.create(userFlux.log("Receiving values !!!"))
                .expectNext(user)
                .verifyComplete();
    }

    @Test
    public void findAll() {
        Flux<User> allFlux = userRepository.findAll();

        StepVerifier.create(allFlux)
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .expectRecordedMatches(elements -> !elements.isEmpty())
                .verifyComplete();
    }
}