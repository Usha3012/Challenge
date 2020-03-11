package com.soniq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InValidSelectionException extends RuntimeException {

    public InValidSelectionException(String msg) {
        super(msg);
    }
}
