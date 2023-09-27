package uz.payme.paymepkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.payme.paymepkg.entity.MerchantTransaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<MerchantTransaction, String> {
    MerchantTransaction findByOrderId(Long id);
}
