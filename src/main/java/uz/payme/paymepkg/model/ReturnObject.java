package uz.payme.paymepkg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReturnObject {
    private String id;

    private TransactionResponse data;
}
