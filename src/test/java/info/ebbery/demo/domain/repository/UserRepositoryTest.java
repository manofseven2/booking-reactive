package info.ebbery.demo.domain.repository;

import info.ebbery.demo.domain.model.InitialCreditLimit;
import info.ebbery.demo.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void findByEmail(){
        User user = userRepository.findByEmail("kjhkjdfhkfkd");
        assertNull(user);
    }
    @Test
    public void insertTest(){
        User user = new User();
        String email = UUID.randomUUID().toString() + "@gmail.com";
        user.setEmail(email);
        user.setName(UUID.randomUUID().toString());
        user.setFamily(UUID.randomUUID().toString());
        InitialCreditLimit limit = new InitialCreditLimit();
        limit.setAmount(1000L);
        limit.setUser(user);
        user.setInitialCreditLimit(limit);
        userRepository.save(user);
        assertNotNull(userRepository.findByEmail(email));
    }
    @Test
    public void findAll(){
        List<User> users = userRepository.findAll();
        System.out.println(users);
        assertTrue(users.size()>0);
    }
}