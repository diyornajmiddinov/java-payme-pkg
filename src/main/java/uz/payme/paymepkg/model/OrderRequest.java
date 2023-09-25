package uz.payme.paymepkg.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderRequest implements Serializable {
    private Long id;
    private Integer amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
