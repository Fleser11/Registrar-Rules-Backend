package org.formalmethods.registrarproject.exception;

import java.lang.RuntimeException;

public class MissingAuditException extends RuntimeException {
    public MissingAuditException(String message) {
        super(message);
    }
}