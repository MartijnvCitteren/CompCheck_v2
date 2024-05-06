package com.martijn.CompCheckV2.exceptions.custom;

import com.martijn.CompCheckV2.exceptions.ExceptionMessages;
import io.jsonwebtoken.JwtException;

public class NotLoggedInException extends JwtException {
    public NotLoggedInException() {
        super(ExceptionMessages.NOT_LOGGED_IN);
    }
}
