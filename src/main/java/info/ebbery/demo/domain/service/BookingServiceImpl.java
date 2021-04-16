package info.ebbery.demo.domain.service;

import info.ebbery.demo.domain.model.Reservation;
import info.ebbery.demo.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingServiceImpl implements BookingService {
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User getUserWithEmail(String email) {
        return null;
    }

    @Override
    public List<Reservation> getAllFailedBookings() {
        return null;
    }
}
