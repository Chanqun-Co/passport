package io.sharing.passport.exception;

public class PassportExpiredException extends RuntimeException {
    public PassportExpiredException(String message) {
        super(message.replaceAll("JWT", "Passport"));
    }

    public PassportExpiredException(String message, Throwable cause) {
        super(message.replaceAll("JWT", "Passport"), cause);
    }
}