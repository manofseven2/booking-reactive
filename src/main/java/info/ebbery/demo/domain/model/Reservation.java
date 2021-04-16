package info.ebbery.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Reservation extends DomainModel{
    private Long cost;
    private String transactionNumber;
    private Date bookingDate=new Date();
    private User user;
    private Boolean successful;
}
