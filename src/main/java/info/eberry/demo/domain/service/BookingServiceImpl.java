package info.eberry.demo.domain.service;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.repository.ReservationRepository;
import info.eberry.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServiceImpl implements BookingService {
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User getUserWithEmail(String email) {
//        return userRepository.findByEmail(email);
        return null;
    }

    @Override
    public List<Reservation> getAllFailedBookings() {
        return null;
    }
}
