package info.eberry.demo.domain.repository;

import info.eberry.demo.domain.model.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Long> {
    Flux<Reservation> findAllBySuccessful(Boolean isSuccessful);

}
