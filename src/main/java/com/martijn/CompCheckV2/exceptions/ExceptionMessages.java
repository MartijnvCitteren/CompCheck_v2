package com.martijn.CompCheckV2.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {
    public static final String LOGIN_CREDENTIALS_NOT_FOUND = "Your email and/or password is incorrect or might not exist.";
}
