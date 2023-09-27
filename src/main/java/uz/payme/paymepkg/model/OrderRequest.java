package uz.payme.paymepkg.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderRequest implements Serializable {
    private Long id;
    @NotNull
    private Integer amount;
    @NotNull
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
