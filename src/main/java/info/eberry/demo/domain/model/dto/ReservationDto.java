package info.eberry.demo.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private String name;
    private String family;
    private String email;
    private Long cost;
    private String transactionNumber;
}
