package io.sharing.passport.core.exception;

public class PassportExpiredException extends RuntimeException {
    public PassportExpiredException(String message) {
        super(message.replaceAll("JWT", "Passport"));
    }

    public PassportExpiredException(String message, Throwable cause) {
        super(message.replaceAll("JWT", "Passport"), cause);
    }
}