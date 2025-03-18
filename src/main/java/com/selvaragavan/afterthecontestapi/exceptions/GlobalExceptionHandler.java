package com.selvaragavan.afterthecontestapi.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MailException.class)
    public ResponseEntity<Object> handleMailException(MailException e) {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("message", e.getMessage());
        return new ResponseEntity<>(mp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException e) {
        HashMap<String, String> mp = new HashMap<>();
        mp.put("message", e.getMessage());
        return new ResponseEntity<>(mp, HttpStatus.UNAUTHORIZED);
    }
}
