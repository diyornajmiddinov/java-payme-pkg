package uz.payme.paymepkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.payme.paymepkg.entity.SuccessfulOrder;

import java.util.Optional;

public interface SuccessfulOrderRepository extends JpaRepository<SuccessfulOrder, Long> {

    Optional<SuccessfulOrder> findByOrderId(Long orderId);

}
