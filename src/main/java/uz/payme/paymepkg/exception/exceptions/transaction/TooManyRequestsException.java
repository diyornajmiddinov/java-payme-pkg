package uz.payme.paymepkg.exception.exceptions.transaction;

import java.util.Map;

public class TooManyRequestsException extends BasePaymeException {
    public TooManyRequestsException() {
        super(200, -31099, Map.of(
                "uz", "Buyurtma tolovni amalga oshirish jarayonida",
                "ru", "Транзакция в очереди",
                "en", "Order payment status is queued"));
    }

    public TooManyRequestsException(String message) {
        super(200, -31099, message);
    }

    public TooManyRequestsException(Object data) {
        super(200, -31099, Map.of(
                "uz", "Buyurtma tolovni amalga oshirish jarayonida",
                "ru", "Транзакция в очереди",
                "en", "Order payment status is queued"), data);
    }

}
