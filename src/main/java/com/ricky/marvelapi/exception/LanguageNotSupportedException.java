package com.ricky.marvelapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LanguageNotSupportedException extends RuntimeException {

    public LanguageNotSupportedException(String exception) {
        super(exception);
        // todo - return supported languages, including default
    }
}
