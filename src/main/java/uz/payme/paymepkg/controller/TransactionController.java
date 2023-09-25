package uz.payme.paymepkg.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.payme.paymepkg.model.TransactionRequest;
import uz.payme.paymepkg.model.ReturnObject;
import uz.payme.paymepkg.model.TransactionResponse;
import uz.payme.paymepkg.service.PaycomService;
import uz.payme.paymepkg.service.TransactionService;
import uz.payme.paymepkg.util.ParamUtil;

import java.util.HashMap;

@RestController
@RequestMapping("/payments/merchant")
@RequiredArgsConstructor
public class TransactionController {

    private final ParamUtil paramUtil;

    private final PaycomService paycomService;

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponse post(@RequestBody TransactionRequest request, HttpServletRequest httpRequest) throws Exception {
        String method = "";
        ReturnObject response = null;
        String password = httpRequest.getHeader("Authorization");
        if (paycomService.authorize(password)) {
            method = paycomService.getPaycomMethodByName(request.getMethod());
            HashMap<String, Object> params = request.getParams();
            System.out.println(params);
            switch (method) {
                case "CheckPerformTransaction":
                    response = transactionService.checkPerformTransaction(params);
                    break;
                case "CreateTransaction":
                    response = transactionService.createTransaction(params);
                    break;
                case "PerformTransaction":
                    response = transactionService.performTransaction(params);
                    break;
                case "CancelTransaction":
                    response = transactionService.cancelTransaction(params);
                    break;
                case "CheckTransaction":
                    response = transactionService.checkTransaction(params);
                    break;
                case "GetStatement":
                    break;
            }
        }
        switch (method) {
            case "CreateTransaction":
                break;
            case "PerformTransaction":
                break;
            case "CancelTransaction":
                break;
        }
        return response != null ? response.getData() : null;
    }
}
