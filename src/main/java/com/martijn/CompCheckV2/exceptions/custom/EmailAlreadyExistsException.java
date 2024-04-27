package com.martijn.CompCheckV2.exceptions.custom;

import com.martijn.CompCheckV2.exceptions.ExceptionMessages;
import jakarta.persistence.EntityExistsException;

public class EmailAlreadyExistsException extends EntityExistsException {
    public EmailAlreadyExistsException() {
        super(ExceptionMessages.EMAIL_ALREADY_EXISTS);
    }
}
