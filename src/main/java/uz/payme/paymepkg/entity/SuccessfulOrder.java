package uz.payme.paymepkg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long userId;

    private Long orderId;

    public SuccessfulOrder(Integer amount, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt, Long orderId) {
        this.amount = amount;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orderId = orderId;
    }
}
