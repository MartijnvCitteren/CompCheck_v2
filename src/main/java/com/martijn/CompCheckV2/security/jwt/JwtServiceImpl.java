package com.martijn.CompCheckV2.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtServiceImpl implements jwtService {
    private final String SECRET_KEY = "normally_this_secret_key_will_be_hidden_somewhere_else_but_for_for_now_this_is_okay";

    @Override
    public String generateToken(String email) {
        final long expirationTimeInSeconds = 30 * 24 * 60 * 60 * 60;
        return Jwts.builder()
                .issuer("CompCheck")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTimeInSeconds))
                .subject(email)
                .signWith(creatSecretKey())
                .compact();
    }

    @Override
    public boolean tokenIsValid(String token) {
        try{
            DecodedJWT decodedJWT = verifier.verify(token);
        }

        return true;
    }

    private Key creatSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
