package uz.payme.paymepkg.model.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PerformTransactionResponse {
    @JsonProperty("perform_time")
    private Long performTime;

    @JsonProperty("transaction")
    private String transactionId;

    private Integer state;
}
