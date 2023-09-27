package uz.payme.paymepkg.service;


import org.springframework.http.ResponseEntity;
import uz.payme.paymepkg.model.OrderRequest;

public interface OrderService {
    ResponseEntity<?> add(OrderRequest orderRequest);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getByUserId(Long userId);

    ResponseEntity<?> update(OrderRequest orderRequest);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getAllByUserId(Long userId);

    ResponseEntity<?> getSuccessfulOrder(Long id);
}
