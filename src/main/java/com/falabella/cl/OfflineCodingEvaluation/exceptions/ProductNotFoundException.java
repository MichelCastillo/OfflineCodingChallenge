package com.falabella.cl.OfflineCodingEvaluation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This product doesn't not exist.")
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);

    }
}
