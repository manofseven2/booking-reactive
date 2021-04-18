package info.eberry.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ResponseContainerDto {

    @JsonProperty("Rejected Transactions")
    private List<FailedTransactionDto> failedTransactionList=new ArrayList<>();
}
