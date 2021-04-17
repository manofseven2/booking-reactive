package info.eberry.demo.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseContainerDto {
    @JsonProperty("Rejected Transactions")
    private List<FailedTransactionDto> failedTransactionList;
}
