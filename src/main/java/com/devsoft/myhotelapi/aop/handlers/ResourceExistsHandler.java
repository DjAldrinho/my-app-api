package com.devsoft.myhotelapi.aop.handlers;

import com.devsoft.myhotelapi.builders.ValidationErrorBuilder;
import com.devsoft.myhotelapi.exceptions.db.ResourceExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Map;

@RestControllerAdvice
public class ResourceExistsHandler {

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<Map<String, Object>> handlerExistsByName(ResourceExistsException exception) {
        return ValidationErrorBuilder.getValidationResponse(exception.getMessage(),
                new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
