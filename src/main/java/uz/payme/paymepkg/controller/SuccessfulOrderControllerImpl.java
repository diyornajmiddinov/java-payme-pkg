package uz.payme.paymepkg.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.payme.paymepkg.service.SuccessfulOrderService;

@RestController
@AllArgsConstructor
public class SuccessfulOrderControllerImpl implements SuccessfulOrderController{

    private final SuccessfulOrderService successfulOrderService;
    @Override
    public ResponseEntity<?> get(Long id) {
        return successfulOrderService.get(id);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return successfulOrderService.getAll();
    }
}
