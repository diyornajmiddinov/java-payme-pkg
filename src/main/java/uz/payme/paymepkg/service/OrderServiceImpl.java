package uz.payme.paymepkg.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.payme.paymepkg.entity.MerchantTransaction;
import uz.payme.paymepkg.entity.Order;
import uz.payme.paymepkg.exception.exceptions.order.OrderNotFoundException;
import uz.payme.paymepkg.model.OrderRequest;
import uz.payme.paymepkg.model.OrderResponse;
import uz.payme.paymepkg.model.StatementResponse;
import uz.payme.paymepkg.repository.OrderRepository;
import uz.payme.paymepkg.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<?> add(OrderRequest orderRequest) {
        orderRequest.setCreatedAt(LocalDateTime.now());
        Order order = mapOrderRequestToOrder(orderRequest);
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(Map.of("success", true, "order", mapOrderToOrderResponse(saved)));
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order doesn't exist!"));
        return ResponseEntity.ok(mapOrderToOrderResponse(order));
    }

    @Override
    public ResponseEntity<?> getByUserId(Long userId) {
        Order order = orderRepository.findByUserId(userId).orElseThrow(() -> new OrderNotFoundException("Order doesn't exist!"));
        return ResponseEntity.ok(mapOrderToOrderResponse(order));
    }

    @Override
    public ResponseEntity<?> update(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(mapOrderListToOrderResponseList(orderRepository.findAll()));
    }


    @Override
    public ResponseEntity<?> getAllByUserId(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return ResponseEntity.ok(mapOrderListToOrderResponseList(orders));
    }

    @Override
    public ResponseEntity<?> getSuccessfulOrder(Long id) {
        MerchantTransaction transaction = transactionRepository.findByOrderIdSuccessful(id)
                .orElseThrow(() -> new OrderNotFoundException("Order doesn't exist!"));
        return ResponseEntity.ok(Map.of("success", true, "transaction", transactionToStatementResponse(transaction)));
    }

    private Object mapOrderListToOrderResponseList(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {
            orderResponses.add(mapOrderToOrderResponse(order));
        }
        return orderResponses;
    }

    public Order mapOrderRequestToOrder(OrderRequest orderRequest) {
        return new Order(orderRequest.getAmount(), orderRequest.getUserId(), orderRequest.getCreatedAt(), orderRequest.getUpdatedAt());
    }

    public OrderResponse mapOrderToOrderResponse(Order order) {
        return new OrderResponse(order.getId(), order.getAmount(), order.getCreatedAt(), order.getUpdatedAt(), order.getUserId());
    }

    private StatementResponse transactionToStatementResponse(MerchantTransaction transaction) {
        return new StatementResponse(
                transaction.getId(),
                transaction.getTime(),
                transaction.getAmount(),
                Long.valueOf(transaction.getCreatedAtMs()),
                transaction.getPerformTime(),
                transaction.getCancelTime(),
                transaction.getTransactionId(),
                transaction.getState(),
                transaction.getReason()
        );
    }
}
