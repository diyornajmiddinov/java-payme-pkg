package uz.payme.paymepkg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/successful-order")
public interface SuccessfulOrderController {
    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable Long id);
    @GetMapping
    ResponseEntity<?> getAll();
}
