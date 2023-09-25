package uz.payme.paymepkg.model.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckTransactionResponse {
    Integer state;
    @JsonProperty("cancel_time")
    Long cancelTime;
    @JsonProperty("transaction")
    String transactionId;
    Integer reason;
}
