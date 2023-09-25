package uz.payme.paymepkg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ApiException {
    private Map<String, Object> error;
}
