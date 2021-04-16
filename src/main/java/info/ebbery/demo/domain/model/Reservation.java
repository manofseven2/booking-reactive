package info.ebbery.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Basic
    private Long cost;
    @Basic
    private String transactionNumber;
    @Basic
    private Date bookingDate=new Date();
    @Basic
    private Boolean successful;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
}
