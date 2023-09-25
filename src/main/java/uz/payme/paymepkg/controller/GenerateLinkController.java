package uz.payme.paymepkg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.payme.paymepkg.model.LinkRequest;
import uz.payme.paymepkg.model.LinkResponse;

@RequestMapping("/api/generate-link")
public interface GenerateLinkController {
    @PostMapping
    ResponseEntity<LinkResponse> generateLink(@RequestBody LinkRequest linkRequest);
}
