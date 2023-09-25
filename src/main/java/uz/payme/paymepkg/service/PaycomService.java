package uz.payme.paymepkg.service;

import org.springframework.http.ResponseEntity;
import uz.payme.paymepkg.model.LinkRequest;
import uz.payme.paymepkg.model.LinkResponse;

public interface PaycomService {
    public String getPaycomMethodByName(String incomingMethod);

    public boolean authorize(String password);

    ResponseEntity<LinkResponse> generatePayLink(LinkRequest linkRequest);
}
