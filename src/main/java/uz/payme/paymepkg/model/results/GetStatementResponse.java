package uz.payme.paymepkg.model.results;

import lombok.Getter;
import lombok.Setter;
import uz.payme.paymepkg.entity.MerchantTransaction;
import uz.payme.paymepkg.model.StatementResponse;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class GetStatementResponse {
    private List<StatementResponse> transactions;

    public GetStatementResponse(List<StatementResponse> transactions) {
        setTransactions(transactions);
    }
}
