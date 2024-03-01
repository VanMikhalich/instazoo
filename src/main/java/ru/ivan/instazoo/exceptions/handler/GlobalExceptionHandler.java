package ru.ivan.instazoo.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ivan.instazoo.exceptions.PostNotFoundException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Map<String, String>> handler(PostNotFoundException exception){
        return new ResponseEntity<>(Map.of("info", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
