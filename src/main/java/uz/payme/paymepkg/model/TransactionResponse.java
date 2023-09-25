package uz.payme.paymepkg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import uz.payme.paymepkg.model.results.*;
import uz.payme.paymepkg.util.Serializer;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse implements Serializable {

    private Object result;

    public TransactionResponse(String createTimeMs, Long performTime, Long cancelTime,
                               String transactionId, Integer state, Integer reason) {
        result = new CancelTransactionResponse(Long.valueOf(createTimeMs), performTime, cancelTime, transactionId, state, reason);
    }

    public TransactionResponse(Integer state, Long cancelTime, String transactionId, Integer reason) {
        result = new CheckTransactionResponse(state, cancelTime, transactionId, reason);
    }

    public TransactionResponse(Boolean allow) {
        result = new CheckPerformTransactionResponse(allow);
    }

    public TransactionResponse(Long performTime, String transactionId, Integer state) {
        result = new PerformTransactionResponse(performTime, transactionId, state);
    }

    public TransactionResponse(String createTime, String transactionId, Integer state) {
        result = new CreateTransactionResponse(Long.valueOf(createTime), transactionId, state);
    }


}

