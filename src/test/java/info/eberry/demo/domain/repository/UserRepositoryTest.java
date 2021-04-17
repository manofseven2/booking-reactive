package info.eberry.demo.domain.repository;

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
    public void findByEmail(){
        Mono<User> user = userRepository.findByEmail("kjhkjdfhkfkd");
        user.doOnNext(u -> assertNull(u));
    }
    @Test
    public void findByEmailExistEmail(){
        User user = new User(1L, "Amir", "Azimi", "amir.azimi.alasti@gmail.com");
        Mono<User> userFlux = userRepository.findByEmail("amir.azimi.alasti@gmail.com");
        StepVerifier.create(userFlux.log("Receiving values !!!"))
                .expectNext(user)
                .verifyComplete();
    }
    @Test
    public void insertTest(){
        User user = new User();
        String email = UUID.randomUUID().toString() + "@gmail.com";
        user.setEmail(email);
        user.setName(UUID.randomUUID().toString());
        user.setFamily(UUID.randomUUID().toString());
        userRepository.save(user).doOnNext(u -> {
            InitialCreditLimit limit = new InitialCreditLimit();
            limit.setAmount(1000L);
            limit.setUserId(u.getId());
            limitRepository.save(limit).subscribe();
        }).subscribe();

        Mono<User> flux = userRepository.findByEmail(email);
        flux.doOnNext(user1 -> assertNotNull(user1)).subscribe();
    }
    @Test
    public void findAll(){
        Flux<User> allFlux = userRepository.findAll();
        List<User> users = new ArrayList<>();
        allFlux.doOnNext(user -> {
            users.add(user);
            log.info(user.toString());
        }).blockLast(Duration.ofSeconds(10));
    }
}