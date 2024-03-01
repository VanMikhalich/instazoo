package ru.ivan.instazoo.exceptions;

public class AccessDeniedException extends Throwable {
    public AccessDeniedException(String message) {
        super(message);
    }
}
