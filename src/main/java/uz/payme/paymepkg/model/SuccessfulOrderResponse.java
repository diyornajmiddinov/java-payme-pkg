package uz.payme.paymepkg.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulOrderResponse {


    private Integer amount;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("order_id")
    private Long orderId;

    public SuccessfulOrderResponse(Integer amount, Long userId, Long orderId) {
        this.amount = amount;
        this.userId = userId;
        this.orderId = orderId;
    }
}
