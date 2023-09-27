package uz.payme.paymepkg.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import uz.payme.paymepkg.model.SuccessfulOrderResponse;

@FeignClient(value = "telegram",url = "http://188.225.26.222:8000/api")
public interface TelegramClient {
    @PostMapping("/success")
    void post(SuccessfulOrderResponse successfulOrderResponse);
}


