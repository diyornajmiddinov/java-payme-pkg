package uz.payme.paymepkg.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.payme.paymepkg.model.OrderRequest;
import uz.payme.paymepkg.service.OrderService;

@RestController
@AllArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public ResponseEntity<?> add(OrderRequest orderRequest) {
        return orderService.add(orderRequest);
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        return orderService.get(id);
    }

    @Override
    public ResponseEntity<?> getByUserId(Long userId) {
        return orderService.getByUserId(userId);
    }

    @Override
    public ResponseEntity<?> update(OrderRequest orderRequest) {
        return orderService.update(orderRequest);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return orderService.getAll();
    }

    @Override
    public ResponseEntity<?> getAllByUserId(Long userId) {
        return orderService.getAllByUserId(userId);
    }

    @Override
    public ResponseEntity<?> getSuccessfulOrder(Long id) {
        return orderService.getSuccessfulOrder(id);
    }
}
