package uz.payme.paymepkg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaycomApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaycomApplication.class, args);
	}

}
