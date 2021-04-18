package info.eberry.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FailedTransactionDto {
    @JsonProperty("First Name")
    private String name;
    @JsonProperty("Last Name")
    private String family;
    @JsonProperty("Email Id")
    private String email;
    @JsonProperty("Transaction Number")
    private String transactionNumber;

}
