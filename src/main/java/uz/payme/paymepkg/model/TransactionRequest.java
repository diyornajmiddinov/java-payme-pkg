package uz.payme.paymepkg.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
public class TransactionRequest implements Serializable {

    private String jsonrpc;

    private Integer id;

    private String method;

    private HashMap<String, Object> params;

}
