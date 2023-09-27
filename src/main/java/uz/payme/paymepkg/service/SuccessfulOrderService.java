package uz.payme.paymepkg.service;


import org.springframework.http.ResponseEntity;

public interface SuccessfulOrderService {
    void add(String transactionId);

    void sendPostRequestToBot(String transactionId);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

}
