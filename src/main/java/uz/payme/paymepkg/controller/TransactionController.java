package uz.payme.paymepkg.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.payme.paymepkg.model.ReturnObject;
import uz.payme.paymepkg.model.TransactionRequest;
import uz.payme.paymepkg.model.TransactionResponse;
import uz.payme.paymepkg.service.PaycomService;
import uz.payme.paymepkg.service.SuccessfulOrderService;
import uz.payme.paymepkg.service.TransactionService;

import java.util.HashMap;

@RestController
@RequestMapping("/api/payments/merchant")
@RequiredArgsConstructor
public class TransactionController {

    private final PaycomService paycomService;

    private final TransactionService transactionService;

    private final SuccessfulOrderService successfulOrderService;

    @PostMapping
    public TransactionResponse post(@RequestBody TransactionRequest request, HttpServletRequest httpRequest){
        String method = "";
        ReturnObject response = null;
        String password = httpRequest.getHeader("Authorization");
        if (paycomService.authorize(password)) {
            method = paycomService.getPaycomMethodByName(request.getMethod());
            HashMap<String, Object> params = request.getParams();
            switch (method) {
                case "CheckPerformTransaction" -> response = transactionService.checkPerformTransaction(params);
                case "CreateTransaction" -> response = transactionService.createTransaction(params);
                case "PerformTransaction" -> response = transactionService.performTransaction(params);
                case "CancelTransaction" -> response = transactionService.cancelTransaction(params);
                case "CheckTransaction" -> response = transactionService.checkTransaction(params);
                case "GetStatement" -> response = transactionService.getStatement(params);
            }
        }
        switch (method) {
            case "CreateTransaction":
                break;
            case "PerformTransaction":
                successfulOrderService.sendPostRequestToBot(response.getId());
                break;
            case "CancelTransaction":
                break;
        }
        return response != null ? response.getData() : null;
    }
}
