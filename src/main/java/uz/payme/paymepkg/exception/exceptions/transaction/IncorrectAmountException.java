package uz.payme.paymepkg.exception.exceptions.transaction;

import java.util.Map;

public class IncorrectAmountException extends BasePaymeException {

    public IncorrectAmountException() {
        super(200, -31001, Map.of(
                "ru", "Неверная сумма",
                "uz", "Noto'g'ri miqdor",
                "en", "Incorrect amount"));
    }

    public IncorrectAmountException(String message) {
        super(200, -31001, message);
    }

    public IncorrectAmountException(Object data) {
        super(200, -31001, Map.of(
                "ru", "Неверная сумма",
                "uz", "Noto'g'ri miqdor",
                "en", "Incorrect amount"), data);
    }
}
