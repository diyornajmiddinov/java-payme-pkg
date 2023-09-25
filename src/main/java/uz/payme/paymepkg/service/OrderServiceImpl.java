package uz.payme.paymepkg.service;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.payme.paymepkg.entity.Order;
import uz.payme.paymepkg.exception.exceptions.order.OrderNotFoundException;
import uz.payme.paymepkg.model.OrderRequest;
import uz.payme.paymepkg.model.OrderResponse;
import uz.payme.paymepkg.repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> add(OrderRequest orderRequest) {
        Order order = mapOrderRequestToOrder(orderRequest);
        orderRepository.save(order);
        return ResponseEntity.ok(true);
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order doesn't exist!"));
        return ResponseEntity.ok(mapOrderToOrderResponse(order));
    }

    @Override
    public ResponseEntity<?> update(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return null;
    }

    public Order mapOrderRequestToOrder(OrderRequest orderRequest) {
        return new Order(orderRequest.getAmount(), orderRequest.getCreatedAt(), orderRequest.getUpdatedAt());
    }

    public OrderResponse mapOrderToOrderResponse(Order order){
        return new OrderResponse(order.getId(), order.getAmount(), order.getCreatedAt(), order.getUpdatedAt());
    }
}
