package com.martijn.CompCheckV2.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtServiceImpl implements jwtService {
    private final String secretKey = "normally_this_secret_key_will_be_hidden_somewhere_else_but_for_for_now_this_is_okay";
    private final long experationTimeInSeconds = 30 * 24 * 60 * 60 * 60;

    @Override
    public String generateToken(String email) {
        return Jwts.builder()
                .issuer("CompCheck")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + experationTimeInSeconds))
                .subject(email)
                .signWith(createSecretKey())
                .compact();
    }

    private Key createSecretKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
