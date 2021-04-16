package info.ebbery.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InitialCreditLimit extends DomainModel {
    private Long amount;
    private User user;
}
