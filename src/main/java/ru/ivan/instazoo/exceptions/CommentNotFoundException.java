package ru.ivan.instazoo.exceptions;

public class CommentNotFoundException extends Throwable{
    public CommentNotFoundException(String message) {
        super(message);
    }
}
