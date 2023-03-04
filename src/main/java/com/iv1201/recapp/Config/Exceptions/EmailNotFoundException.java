package com.iv1201.recapp.Config.Exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}