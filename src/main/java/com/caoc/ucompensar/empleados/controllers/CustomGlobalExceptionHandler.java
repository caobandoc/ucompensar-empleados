package com.caoc.ucompensar.empleados.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> customHandleNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("error", "Validation Failed");
        body.put("code", HttpStatus.BAD_REQUEST.value());


        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(HashMap::new, (m, error) -> m.put(error.getField(), error.getDefaultMessage()), HashMap::putAll);

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}