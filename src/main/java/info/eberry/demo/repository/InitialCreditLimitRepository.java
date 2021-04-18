package info.eberry.demo.repository;

import info.eberry.demo.domain.model.InitialCreditLimit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface InitialCreditLimitRepository extends ReactiveCrudRepository<InitialCreditLimit, Long> {
    Mono<InitialCreditLimit> findByUserId(Long userId);
}
