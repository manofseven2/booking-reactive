package info.eberry.demo.domain.controller;

import info.eberry.demo.domain.exception.DemoException;
import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.User;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import info.eberry.demo.domain.model.dto.ReservationDto;
import info.eberry.demo.domain.model.dto.ResponseContainerDto;
import info.eberry.demo.domain.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "failed/values", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Reservation> getAllFailedReservations(){
        return bookingService.getAllFailedBookings();
    }

    @GetMapping(value = "failed/dtos", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<FailedTransactionDto> getAllFailedBookingsDtos(){
        return bookingService.getAllFailedBookingsDtos();
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<Reservation> save(@RequestBody String req){
        if(req == null || req.isEmpty())
            throw new DemoException();
        String[] parts = req.split(",");
        if(parts.length != 5)
            throw new DemoException();
        ReservationDto dto = new ReservationDto(parts[0].trim(), parts[1].trim(), parts[2].trim(),  Long.parseLong(parts[3].trim()), parts[4].trim());
        return bookingService.saveReservation(dto);
    }
/*
    @GetMapping(value = "failed/general", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseContainerDto> getAllFailedBookingsGeneralDto(){
        Flux<FailedTransactionDto> allFailedBookingsDtos = bookingService.getAllFailedBookingsDtos();
        ResponseContainerDto dto = new ResponseContainerDto();
        Mono<ResponseContainerDto> monoResp = Mono.just(dto);
        Flux<Mono<ResponseContainerDto>> map = allFailedBookingsDtos.map(r -> monoResp.map(d -> d.getFailedTransactionList().add(r)));

        return map.;
    }
*/
}
