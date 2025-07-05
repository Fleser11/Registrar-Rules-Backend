package org.formalmethods.registrarproject.exception;

import java.lang.RuntimeException;

public class MissingItemException extends RuntimeException {
    public MissingItemException(String message) {
        super(message);
    }
}