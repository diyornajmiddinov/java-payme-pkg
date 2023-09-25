package uz.payme.paymepkg.exception.exceptions.transaction;

public class PermissionDeniedException extends BasePaymeException {

    public PermissionDeniedException() {
        super(200, -32504, "Permission denied");
    }
    public PermissionDeniedException(Object data) {
        super(200, -32504, "Permission denied", data);
    }

}
