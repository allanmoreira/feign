package br.com.moreirallan.feign.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class DecodeComponent {

    public <T> T fromJwt(String jwt, Class<T> clazz) {
        DecodedJWT decodedJWT = JWT.decode(jwt);
        String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(decodedJWT.getPayload()));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(payloadJson, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JWT para objeto", e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para objeto", e);
        }
    }
}
