package uz.payme.paymepkg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.payme.paymepkg.exception.exceptions.transaction.MethodNotFoundException;
import uz.payme.paymepkg.exception.exceptions.transaction.PermissionDeniedException;
import uz.payme.paymepkg.model.LinkRequest;
import uz.payme.paymepkg.model.LinkResponse;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class PaycomServiceImpl implements PaycomService {
    private final Logger log = LoggerFactory.getLogger(Service.class);

    @Value("${payme.key}")
    private String PAYME_KEY;

    @Value("${payme.id}")
    private String PAYME_ID;
    @Value("${payme.url}")
    private String PAYME_URL;
    @Value("${payme.account}")
    private String PAYME_ACCOUNT;
    @Value("${payme.url}")
    private String PAYME_CALL_BACK_URL;

    @Override
    public ResponseEntity<LinkResponse> generatePayLink(LinkRequest linkRequest) {
        String params = String.format("m=%s;ac.%s=%s;a=%s;c=%s", PAYME_ID, PAYME_ACCOUNT, linkRequest.getId(), linkRequest.getAmount(), PAYME_CALL_BACK_URL);
        String encode_params = Base64.getEncoder().encodeToString(params.getBytes(StandardCharsets.UTF_8));
        return ResponseEntity.ok(new LinkResponse(String.format("%s/%s", PAYME_URL, encode_params), true));
    }

    public boolean authorize(String password) {
        boolean isPayme = false;
        check_password(password);
        String encryptedPassword = getEncryptedPassword(password, " ");
        String merchantKey = getEncryptedPassword(decodePassword(encryptedPassword), ":");

        if (merchantKey.equals(PAYME_KEY))
            isPayme = true;

        if (!merchantKey.equals(PAYME_KEY))
            log.warn("Invalid key in request!");

        if (!isPayme) {
            log.error("Unavailable data for unauthorized users!");
            throw new PermissionDeniedException("Unavailable data for unauthorized users!");
        }

        return true;
    }

    private String decodePassword(String encryptedPassword) {
        String errorMessage;
        String password;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
            password = new String(decodedBytes, StandardCharsets.UTF_8);
        } catch (RuntimeException e) {
            errorMessage = "Error when authorize request to merchant!";
            log.error(errorMessage);
            throw new PermissionDeniedException(errorMessage);
        }
        return password;
    }

    private static String getEncryptedPassword(String password, String space) {
        String[] parts = password.split(space);
        return parts[parts.length - 1];
    }

    private void check_password(String password) {
        if (password == null) {
            String errorMessage = "Request from an unauthorized source!";
            log.error(errorMessage);
            throw new PermissionDeniedException(errorMessage);
        }
    }

    public String getPaycomMethodByName(String incomingMethod) {
        String method;
        Map<String, String> availableMethods = Map.of(
                "CheckPerformTransaction", "CheckPerformTransaction",
                "CreateTransaction", "CreateTransaction",
                "PerformTransaction", "PerformTransaction",
                "CancelTransaction", "CancelTransaction",
                "CheckTransaction", "CheckTransaction",
                "GetStatement", "GetStatement");
        try {
            method = availableMethods.get(incomingMethod);
        } catch (NullPointerException e) {
            String errorMessage = "Unavailable method: " + incomingMethod;
            throw new MethodNotFoundException(errorMessage);
        }

        return method;
    }
}
