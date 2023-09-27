package uz.payme.paymepkg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.payme.paymepkg.entity.MerchantTransaction;
import uz.payme.paymepkg.entity.Order;
import uz.payme.paymepkg.entity.SuccessfulOrder;
import uz.payme.paymepkg.exception.exceptions.order.OrderNotFoundException;
import uz.payme.paymepkg.repository.OrderRepository;
import uz.payme.paymepkg.repository.SuccessfulOrderRepository;
import uz.payme.paymepkg.repository.TransactionRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuccessfulOrderServiceImpl implements SuccessfulOrderService {

    private final SuccessfulOrderRepository successfulOrderRepository;

    private final TransactionRepository transactionRepository;

    private final OrderRepository orderRepository;

    @Override
    public void add(String transactionId) {
        Optional<MerchantTransaction> merchantTransactionOptional = transactionRepository.
                findById(transactionId);
        if (merchantTransactionOptional.isEmpty())
            return;
        MerchantTransaction merchantTransaction = merchantTransactionOptional.get();
        Long orderId = merchantTransaction.getOrderId();
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty())
            return;
        Order order = orderOptional.get();
        successfulOrderRepository.save(mapOrderToSuccessfulOrder(order));
    }


    @Override
    public ResponseEntity<?> get(Long id) {
        SuccessfulOrder order = successfulOrderRepository.findByOrderId(id)
                .orElseThrow(() -> new OrderNotFoundException("Successful order doesn't exist!"));
        return ResponseEntity.ok(Map.of("success",true, "order", order));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<SuccessfulOrder> all = successfulOrderRepository.findAll();
        return ResponseEntity.ok(Map.of("success",true, "orders",all));
    }

    private SuccessfulOrder mapOrderToSuccessfulOrder(Order order) {
        return new SuccessfulOrder(
                order.getAmount(),
                order.getUserId(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getId()
        );
    }

}
