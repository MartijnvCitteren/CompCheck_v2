package com.martijn.CompCheckV2.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {
    public static final String LOGIN_CREDENTIALS_NOT_FOUND = "Your email and/or password is incorrect or might not exist.";
    public static final String EMAIL_ALREADY_EXISTS = "This email does already exist. Please choose another one or reset your password.";
    public static final String NOT_LOGGED_IN ="You're not (yet) logged-in. Please try to login again";
}
