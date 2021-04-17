package info.eberry.demo.domain.repository;

import info.eberry.demo.domain.model.Reservation;
import info.eberry.demo.domain.model.dto.FailedTransactionDto;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Long> {
    Flux<Reservation> findAllBySuccessful(Boolean isSuccessful);
    @Query("select u.name, u.family, u.email, r.transaction_number from user u join reservation r on u.id = r.user_id where r.successful=false")
    Flux<FailedTransactionDto> findAllFailedReservations();

}
