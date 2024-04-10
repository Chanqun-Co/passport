package io.sharing.passport.core.exception;

public class UnAuthenticatedPassportException extends RuntimeException {
    public UnAuthenticatedPassportException() {
    }

    public UnAuthenticatedPassportException(String message) {
        super(message);
    }

    public UnAuthenticatedPassportException(String message, Throwable cause) {
        super(message, cause);
    }
}
