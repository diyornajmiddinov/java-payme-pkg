package uz.payme.paymepkg.exception.exceptions.transaction;

public class MethodNotFoundException extends BasePaymeException {

    public MethodNotFoundException() {
        super(405, -32601, "Method not found");
    }

    public MethodNotFoundException(String message) {
        super(405, -32601, message);
    }

    public MethodNotFoundException(Object data) {
        super(405, -32601, "Method not found", data);
    }

}
