package info.ebbery.demo.domain.service;

import info.ebbery.demo.domain.model.Reservation;
import info.ebbery.demo.domain.model.User;

import java.util.List;

public interface BookingService {
    User saveUser(User user);
    User getUserWithEmail(String email);
    List<Reservation> getAllFailedBookings();
}
