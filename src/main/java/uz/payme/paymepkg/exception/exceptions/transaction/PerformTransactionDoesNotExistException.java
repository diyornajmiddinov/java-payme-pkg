package uz.payme.paymepkg.exception.exceptions.transaction;

import java.util.Map;

public class PerformTransactionDoesNotExistException extends BasePaymeException{
    public PerformTransactionDoesNotExistException() {
        super(200, -31050, Map.of(
                "ru", "Заказ не существует",
                "uz", "Buyurtma topilmadi",
                "en", "Order does not exists"));
    }
    public PerformTransactionDoesNotExistException(Integer errorCode) {
        super(200, errorCode, Map.of(
                "ru", "Заказ не существует",
                "uz", "Buyurtma topilmadi",
                "en", "Order does not exists"));
    }

    public PerformTransactionDoesNotExistException(String message) {
        super(200, -31050, message);
    }

    public PerformTransactionDoesNotExistException(Object data) {
        super(200, -31050, Map.of(
                "ru", "Заказ не существует",
                "uz", "Buyurtma topilmadi",
                "en", "Order does not exists"), data);
    }
}
