package uz.payme.paymepkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.payme.paymepkg.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.userId = :userId ORDER BY o.createdAt DESC LIMIT 1")
    Optional<Order> findByUserId(@Param("userId") Long userId);

    List<Order> findAllByUserId(Long userId);
}
