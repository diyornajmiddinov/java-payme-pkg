package uz.payme.paymepkg.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "merchant_transactions")
@Getter
@Setter
public class MerchantTransaction {

    @Id
    private String id;

    private String transactionId;

    private Long orderId;

    private Float amount;

    private Long time;

    private Long performTime = 0L;

    private Long cancelTime = 0L;

    private Integer state = 1;

    private Integer reason;

    private String createdAtMs;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
