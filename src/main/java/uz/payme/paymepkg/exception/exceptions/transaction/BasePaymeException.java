package uz.payme.paymepkg.exception.exceptions.transaction;

public class BasePaymeException extends RuntimeException {

    protected int statusCode;
    protected int errorCode;
    protected Object message;

    protected Object data;

    public BasePaymeException(int statusCode, int errorCode, Object message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public BasePaymeException(int statusCode, int errorCode, Object message, Object data) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }


    public int getErrorCode() {
        return errorCode;
    }


    public Object getInfo() {
        return message;
    }


    public Object getData() {
        return data;
    }

}


