package uz.payme.paymepkg.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Serializer {
    private final ParamUtil paramUtil;

    public HashMap<String, Object> getValidateData(HashMap<String, Object> params) {
        return paramUtil.getParams(params);
    }
}
