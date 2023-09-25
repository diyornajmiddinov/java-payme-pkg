package uz.payme.paymepkg.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Order(Integer amount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}