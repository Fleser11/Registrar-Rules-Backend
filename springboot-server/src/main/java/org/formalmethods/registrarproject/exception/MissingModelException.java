package org.formalmethods.registrarproject.Exceptions;

import java.lang.RuntimeException;

public class MissingModelException extends RuntimeException {
    public MissingModelException(String message) {
        super(message);
    }
}