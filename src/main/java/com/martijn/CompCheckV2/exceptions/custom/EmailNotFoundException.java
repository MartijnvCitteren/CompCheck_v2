package com.martijn.CompCheckV2.exceptions.custom;

import com.martijn.CompCheckV2.exceptions.ExceptionMessages;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    public EmailNotFoundException() {
        super(ExceptionMessages.LOGIN_CREDENTIALS_NOT_FOUND);
    }

}
