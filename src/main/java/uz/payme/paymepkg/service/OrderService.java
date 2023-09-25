package uz.payme.paymepkg.service;


import org.springframework.http.ResponseEntity;
import uz.payme.paymepkg.model.OrderRequest;
import uz.payme.paymepkg.model.OrderResponse;

import java.util.List;

public interface OrderService {
    ResponseEntity<?> add(OrderRequest orderRequest);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> update(OrderRequest orderRequest);

    ResponseEntity<?> getAll();
}
