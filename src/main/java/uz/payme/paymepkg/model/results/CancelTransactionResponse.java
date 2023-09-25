package uz.payme.paymepkg.model.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CancelTransactionResponse {
    @JsonProperty("create_time")
    Long createTimeMs;
    @JsonProperty("perform_time")
    Long performTime;
    @JsonProperty("cancel_time")
    Long cancelTime;
    @JsonProperty("transaction")
    String transactionId;
    Integer state;
    Integer reason;
}
