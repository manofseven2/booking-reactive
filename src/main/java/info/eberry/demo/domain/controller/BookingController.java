package info.eberry.demo.domain.controller;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/resources/bookings")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/id/{id}")
    public Mono<User> getUserById(@PathVariable("id")Long id){
        return bookingService.getUserById(id);
    }
    @GetMapping("{email}")
    public Mono<User> getBookingByEmail(@PathVariable("email") String email){
        return bookingService.getUserWithEmail(email);
    }

    @GetMapping(value = "failed", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Reservation> getAllFailedReservations(){
        return bookingService.getAllFailedBookings();
    }
}
