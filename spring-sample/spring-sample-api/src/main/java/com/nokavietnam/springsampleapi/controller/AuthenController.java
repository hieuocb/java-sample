package com.nokavietnam.springsampleapi.controller;

import com.nokavietnam.springsampleapi.model.NotiTokenRequest;
import com.nokavietnam.springsampleapi.model.NotiTokenResponse;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenController {

    private final long JWT_EXPIRATION = 604_800_000L;
    public static final String JWT_SECRET = "helloocb";

    public static final String SECRET_KEY = "hello_world";

    @PostMapping(value = "/token")
    public ResponseEntity<NotiTokenResponse> getToken(@RequestBody NotiTokenRequest requestBody) {
        try {
            NotiTokenResponse response = new NotiTokenResponse();
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//            String token = Jwts.builder()
//                    .setSubject(UUID.randomUUID().toString())
//                    .setIssuedAt(now)
//                    .setExpiration(expiryDate)
//                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//                    .compact();

            var token = createJWT(UUID.randomUUID().toString(), "noka", "noka", System.currentTimeMillis());

            response.setAccessToken(token);
            response.setTokenType("Bearer");
            response.setExpiresIn(expiryDate.toString());
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            NotiTokenResponse response = new NotiTokenResponse();
            response.setError("400");
            response.setErrorMsg(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
}
