package info.eberry.demo.domain.service;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;

import java.util.List;

public interface BookingService {
    User saveUser(User user);
    User getUserWithEmail(String email);
    List<Reservation> getAllFailedBookings();
}
