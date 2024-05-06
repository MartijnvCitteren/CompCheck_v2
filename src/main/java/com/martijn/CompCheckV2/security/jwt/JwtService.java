package com.martijn.CompCheckV2.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(String email);
    boolean tokenIsValid(String token, UserDetails user);
    boolean tokenIsNotExpired(String token);
    String extractEmail(String token);

}
