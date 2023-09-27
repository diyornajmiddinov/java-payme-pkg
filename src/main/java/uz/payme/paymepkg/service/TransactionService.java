package uz.payme.paymepkg.service;

import uz.payme.paymepkg.model.ReturnObject;

import java.util.HashMap;

public interface TransactionService {
    ReturnObject cancelTransaction(HashMap<String, Object> params);

    ReturnObject checkPerformTransaction(HashMap<String, Object> params);

    ReturnObject checkTransaction(HashMap<String, Object> params);

    ReturnObject performTransaction(HashMap<String, Object> params);

    ReturnObject createTransaction(HashMap<String, Object> params);

    ReturnObject getStatement(HashMap<String, Object> params);
}
