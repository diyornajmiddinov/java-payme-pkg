package uz.payme.paymepkg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.payme.paymepkg.entity.MerchantTransaction;
import uz.payme.paymepkg.entity.Order;
import uz.payme.paymepkg.exception.exceptions.transaction.IncorrectAmountException;
import uz.payme.paymepkg.exception.exceptions.transaction.PerformTransactionDoesNotExistException;
import uz.payme.paymepkg.exception.exceptions.transaction.TooManyRequestsException;
import uz.payme.paymepkg.model.StatementResponse;
import uz.payme.paymepkg.model.ReturnObject;
import uz.payme.paymepkg.model.TransactionResponse;
import uz.payme.paymepkg.repository.OrderRepository;
import uz.payme.paymepkg.repository.TransactionRepository;
import uz.payme.paymepkg.util.Serializer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final OrderRepository orderRepository;

    @Value("${payme.min-amount}")
    private Long MIN_AMOUNT;

    private final Serializer serializer;

    @Transactional
    public ReturnObject cancelTransaction(HashMap<String, Object> params) {
        HashMap<String, Object> validateData = serializer.getValidateData(params);
        String transactionId = validateData.get("id").toString();
        MerchantTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new PerformTransactionDoesNotExistException(-31003));

        if (transaction.getCancelTime() == 0) {
            transaction.setCancelTime(LocalDateTime.now().atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());
        }
        if (transaction.getPerformTime() == 0)
            transaction.setState(-1);
        if (transaction.getPerformTime() != 0)
            transaction.setState(-2);

        transaction.setReason(Integer.valueOf(validateData.get("reason").toString()));
        transactionRepository.save(transaction);

        TransactionResponse cancelResponse = new TransactionResponse(
                transaction.getState(),
                transaction.getCancelTime(),
                transaction.getTransactionId(),
                transaction.getReason()
        );

        return new ReturnObject(transaction.getId(), cancelResponse);
    }

    @Transactional
    public ReturnObject checkPerformTransaction(HashMap<String, Object> params) {
        HashMap<String, Object> validateData = serializer.getValidateData(params);
        if (validate(validateData))
            throw new IncorrectAmountException();
        if (validate_amount(validateData))
            throw new IncorrectAmountException("Payment amount is less than allowed.");

        return new ReturnObject(null, new TransactionResponse(true));
    }

    @Transactional
    public ReturnObject checkTransaction(HashMap<String, Object> params) {
        HashMap<String, Object> validateData = serializer.getValidateData(params);
        String transactionId = validateData.get("id").toString();
        MerchantTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new PerformTransactionDoesNotExistException(-31003));

        TransactionResponse checkResponse = new TransactionResponse(
                transaction.getCreatedAtMs(),
                transaction.getPerformTime(),
                transaction.getCancelTime(),
                transaction.getTransactionId(),
                transaction.getState(),
                transaction.getReason()
        );
        return new ReturnObject(null, checkResponse);
    }


    @Override
    @Transactional
    public ReturnObject performTransaction(HashMap<String, Object> params) {
        HashMap<String, Object> validateData = serializer.getValidateData(params);
        String transactionId = validateData.get("id").toString();
        MerchantTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new PerformTransactionDoesNotExistException(-31003));
        transaction.setState(2);
        if (transaction.getPerformTime() == 0) {
            transaction.setPerformTime(LocalDateTime.now().atZone(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli());
        }
        transactionRepository.save(transaction);
        TransactionResponse transactionResponse = new TransactionResponse(
                transaction.getPerformTime(),
                transaction.getTransactionId(),
                transaction.getState(),
                transaction.getId()
        );
        return new ReturnObject(transaction.getId(), transactionResponse);
    }

    @Override
    @Transactional
    public ReturnObject createTransaction(HashMap<String, Object> params) {
        HashMap<String, Object> validateData = serializer.getValidateData(params);
        Long orderId = Long.valueOf(validateData.get("orderId").toString());
        MerchantTransaction transaction = transactionRepository.findByOrderId(orderId);

        if (transaction == null) {
            String id = validateData.get("id").toString();
            Float amount = Float.valueOf(validateData.get("amount").toString());
            MerchantTransaction merchantTransaction = createMerchantTransaction(orderId, id, amount);
            if (validate(validateData))
                throw new IncorrectAmountException();
            if (validate_amount(validateData))
                throw new IncorrectAmountException("Payment amount is less than allowed.");
            transaction = transactionRepository.save(merchantTransaction);
        }

        if (!transaction.getId().equals(validateData.get("id")))
            throw new TooManyRequestsException();

        TransactionResponse transactionResponse = new TransactionResponse(
                transaction.getCreatedAtMs(),
                transaction.getTransactionId(),
                transaction.getState()
        );


        return new ReturnObject(transaction.getId(), transactionResponse);
    }

    @Override
    @Transactional
    public ReturnObject getStatement(HashMap<String, Object> params) {
        HashMap<String, Object> validateData = serializer.getValidateData(params);
        long from = Long.parseLong(validateData.get("startDate").toString());
        long to = Long.parseLong(validateData.get("endDate").toString());
        List<MerchantTransaction> dbTransactions =
                transactionRepository.findAll();
        List<StatementResponse> transactions = sortTransactions(from, to, dbTransactions);
        TransactionResponse transactionResponse = new TransactionResponse(transactions);
        return new ReturnObject(null, transactionResponse);
    }

    private List<StatementResponse> sortTransactions(long from, long to, List<MerchantTransaction> dbTransactions) {
        List<StatementResponse> transactions = new ArrayList<>();
        for (MerchantTransaction transaction : dbTransactions) {
            if (Long.parseLong(transaction.getCreatedAtMs()) >= from &&
                    Long.parseLong(transaction.getCreatedAtMs()) <= to) {

                transactions.add(transactionToStatementResponse(transaction));
            }
        }
        return transactions;
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


    private MerchantTransaction createMerchantTransaction(Long orderId, String id, Float amount) {
        MerchantTransaction merchantTransaction = new MerchantTransaction();
        merchantTransaction.setId(id);
        merchantTransaction.setOrderId(orderId);
        merchantTransaction.setTransactionId(UUID.randomUUID().toString());
        merchantTransaction.setAmount(amount);
        merchantTransaction.setCreatedAtMs(String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()));
        merchantTransaction.setState(1);
        return merchantTransaction;
    }

    public boolean validate(HashMap<String, Object> params) {
        if (params.get("orderId") != null) {
            Long orderId = Long.valueOf(params.get("orderId").toString());
            Order order = orderRepository.findById(orderId).orElseThrow(PerformTransactionDoesNotExistException::new);
            Integer paramAmount = Integer.valueOf(params.get("amount").toString());
            return !Objects.equals(order.getAmount(), paramAmount);
        }
        return false;
    }

    private boolean validate_amount(HashMap<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());
        Order order = orderRepository.findById(orderId).orElseThrow(PerformTransactionDoesNotExistException::new);
        return order.getAmount() <= MIN_AMOUNT;
    }
}
