package info.ebbery.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class InitialCreditLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Basic
    private Long amount;
    @OneToOne
    @JoinColumn(name="USER_ID")
    private User user;
}
