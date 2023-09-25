package uz.payme.paymepkg.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.payme.paymepkg.model.LinkRequest;
import uz.payme.paymepkg.model.LinkResponse;
import uz.payme.paymepkg.service.PaycomService;

@RestController
@AllArgsConstructor
public class GenerateLinkControllerImpl implements GenerateLinkController{

    private final PaycomService paycomService;

    @Override
    public ResponseEntity<LinkResponse> generateLink(LinkRequest linkRequest) {
        return paycomService.generatePayLink(linkRequest);
    }
}
