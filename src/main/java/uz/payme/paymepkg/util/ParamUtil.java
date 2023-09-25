package uz.payme.paymepkg.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ParamUtil {

    @Value("${payme.account}")
    private String PAYME_ACCOUNT;

    public HashMap<String, Object> getParams(HashMap<String, Object> params) {

        HashMap<String, Object> account = (HashMap<String, Object>) params.get("account");

        HashMap<String, Object> cleanParams = new HashMap<>();

        cleanParams.put("id", params.get("id"));
        cleanParams.put("time", params.get("time"));
        cleanParams.put("amount", params.get("amount"));
        cleanParams.put("reason", params.get("reason"));

        cleanParams.put("startDate", params.get("from"));
        cleanParams.put("endDate", params.get("to"));

        if (account != null) {
            cleanParams.put("orderId", account.get(PAYME_ACCOUNT));
        }

        return cleanParams;

    }

    public Integer getOrderId(HashMap<String, Object> params){
        return (Integer) getParams(params).get("orderId");
    }

}