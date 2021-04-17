package info.eberry.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


import java.util.Date;

@Data
@NoArgsConstructor
@Table
public class Reservation {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
//    @Basic
    private Long cost;
//    @Basic
    private String transactionNumber;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingDate=new Date();
//    @Basic
    private Boolean successful;
//    @ManyToOne
    @Column("USER_ID")
    private Long userId;
}
