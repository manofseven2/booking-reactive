package info.eberry.demo.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/resources/bookings")
public class BookingController {
    @GetMapping("{id}")
    public Mono<String> getBookingById(@PathVariable("id") String id){
        return Mono.just("{}");
    }
}
