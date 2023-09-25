package uz.payme.paymepkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.payme.paymepkg.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
