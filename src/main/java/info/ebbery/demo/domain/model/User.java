package info.ebbery.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@ToString(includeFieldNames = true, exclude = {"reservations", "initialCreditLimit"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Basic
    @Column(length = 100)
    private String name;
    @Basic
    @Column(length = 150)
    private String family;
    @Basic
    @Column(length = 150, unique = true)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;
    @OneToOne(mappedBy = "user", cascade=CascadeType.ALL)
    private InitialCreditLimit initialCreditLimit;
}
