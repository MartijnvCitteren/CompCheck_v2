package com.martijn.CompCheckV2.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.martijn.CompCheckV2.exceptions.custom.NotLoggedInException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtServiceImpl implements JwtService {

    @Override
    public String generateToken(String email) {
        final long expirationTimeInSeconds = 30 * 24 * 60 * 60 * 60L;
        return Jwts.builder()
                .issuer("CompCheck")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTimeInSeconds))
                .subject(email)
                .signWith(creatSecretKey())
                .compact();
    }

    @Override
    public boolean tokenIsValid(String token, UserDetails user) {
        String email = extractEmail(token);
        return email.equals(user.getUsername()) && tokenIsNotExpired(token);
    }

    @Override
    public boolean tokenIsNotExpired(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt().before(new Date());
    }

    @Override
    public String extractEmail(String token) {
        return extractAllClaims(token).get("email").toString();
    }


    private SecretKey creatSecretKey() {
        String secretKey = "normally_this_secret_key_will_be_hidden_somewhere_else_but_for_for_now_this_is_okay";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        if (tokenIsNotExpired(token)) {
            return Jwts.parser()
                    .verifyWith(creatSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        throw new NotLoggedInException();
    }

}
