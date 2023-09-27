package uz.payme.paymepkg.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.payme.paymepkg.model.OrderRequest;

@RequestMapping("/api/order")
public interface OrderController {
    @PostMapping
    ResponseEntity<?> add(@RequestBody OrderRequest orderRequest);

    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable Long id);

    @GetMapping("/by-user/{userId}")
    ResponseEntity<?> getByUserId(@PathVariable Long userId);

    @PutMapping
    ResponseEntity<?> update(@RequestBody OrderRequest orderRequest);

    @GetMapping
    ResponseEntity<?> getAll();

    @GetMapping("/by-user/all/{userId}")
    ResponseEntity<?> getAllByUserId(@PathVariable Long userId);
}
