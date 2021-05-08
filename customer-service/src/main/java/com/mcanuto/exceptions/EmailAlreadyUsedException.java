package com.mcanuto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyUsedException extends Exception {
    public EmailAlreadyUsedException() {
        super("Email already used");
    }
}
