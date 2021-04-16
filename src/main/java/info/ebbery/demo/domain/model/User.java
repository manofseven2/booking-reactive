package info.ebbery.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User extends DomainModel {
    private String name;
    private String family;
    private String email;
    private List<Reservation> reservations;
    private InitialCreditLimit initialCreditLimit;
}
