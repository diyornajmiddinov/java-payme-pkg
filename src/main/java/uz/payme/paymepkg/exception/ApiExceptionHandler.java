package uz.payme.paymepkg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.payme.paymepkg.exception.exceptions.order.OrderNotFoundException;
import uz.payme.paymepkg.exception.exceptions.transaction.*;
import uz.payme.paymepkg.model.ApiException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {PermissionDeniedException.class})
    public ResponseEntity<Object> exceptionHandler(PermissionDeniedException e) {
        HttpStatus httpStatus = HttpStatus.valueOf(200);
        Map<String, Object> error = Map.of("code", e.getErrorCode(),
                "message", e.getInfo(),
                "data", e.getData());
        ApiException apiException = new ApiException(error);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {TooManyRequestsException.class})
    public ResponseEntity<Object> exceptionHandler(TooManyRequestsException e) {
        HttpStatus httpStatus = HttpStatus.valueOf(200);
        Map<String, Object> error = new HashMap<>(Map.of("code", e.getErrorCode(),
                "message", e.getInfo()));
        if (e.getData() != null) {
            error.put("data", e.getData());
        }
        ApiException apiException = new ApiException(error);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {MethodNotFoundException.class})
    public ResponseEntity<Object> exceptionHandler(MethodNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.valueOf(200);
        Map<String, Object> error = Map.of("code", e.getErrorCode(),
                "message", e.getInfo(),
                "data", e.getData());
        ApiException apiException = new ApiException(error);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {PerformTransactionDoesNotExistException.class})
    public ResponseEntity<Object> exceptionHandler(PerformTransactionDoesNotExistException e) {
        HttpStatus httpStatus = HttpStatus.valueOf(200);
        Map<String, Object> error = Map.of("code", e.getErrorCode(),
                "message", e.getInfo());
        ApiException apiException = new ApiException(error);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {IncorrectAmountException.class})
    public ResponseEntity<Object> exceptionHandler(IncorrectAmountException e) {
        HttpStatus httpStatus = HttpStatus.valueOf(200);
        Map<String, Object> error = Map.of("code", e.getErrorCode(),
                "message", e.getInfo());
        ApiException apiException = new ApiException(error);
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity<Object> exceptionHandler(OrderNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.valueOf(HttpStatus.NOT_FOUND.value());
        Map<String, Object> error = Map.of("message", e.getMessage());
        ApiException apiException = new ApiException(error);
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
