package info.eberry.demo.domain.repository;

import info.eberry.demo.domain.model.Reservation;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Long> {
    @Query("select m from Reservation m where m.successful=true")
    Flux<Reservation> getAllFailedReservations();
}
