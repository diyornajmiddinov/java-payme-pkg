package uz.payme.paymepkg.model.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionResponse {
    @JsonProperty("create_time")
    Long createTimeMs;

    @JsonProperty("transaction")
    String transactionId;
    Integer state;
}
