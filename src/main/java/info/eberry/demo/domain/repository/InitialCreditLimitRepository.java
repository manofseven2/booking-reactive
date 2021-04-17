package info.eberry.demo.domain.repository;

import info.eberry.demo.domain.model.InitialCreditLimit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialCreditLimitRepository extends ReactiveCrudRepository<InitialCreditLimit, Long> {
}
