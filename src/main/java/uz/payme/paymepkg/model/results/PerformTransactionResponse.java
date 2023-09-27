package uz.payme.paymepkg.model.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PerformTransactionResponse {
    @JsonProperty("perform_time")
    private Long performTime;

    @JsonProperty("transaction")
    private String transactionId;

    private Integer state;

    private String id;
}
