package uz.payme.paymepkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.payme.paymepkg.entity.MerchantTransaction;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<MerchantTransaction, String> {
    MerchantTransaction findByOrderId(Long id);

    @Query("SELECT m FROM MerchantTransaction m WHERE m.orderId = :orderId AND m.state = 2")
    Optional<MerchantTransaction> findByOrderIdSuccessful(@Param("orderId") Long orderId);
}
