package com.devsoft.myhotelapi.aop.handlers;

import com.devsoft.myhotelapi.builders.ValidationErrorBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Map;

@RestControllerAdvice
public class HttpMessageNotReadableHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handlerNoBody(HttpMessageNotReadableException exception) {
        return ValidationErrorBuilder.getValidationResponse(exception.getMostSpecificCause().getMessage(),
                new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}
