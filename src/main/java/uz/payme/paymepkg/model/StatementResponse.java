package uz.payme.paymepkg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatementResponse {
    private String id;
    private Long time;
    private Float amount;
    @JsonProperty("create_time")
    private Long createTime;
    @JsonProperty("perform_time")
    private Long performTime;
    @JsonProperty("cancel_time")
    private Long cancelTime;
    private String transaction;
    private Integer state;
    private Integer reason;
}
