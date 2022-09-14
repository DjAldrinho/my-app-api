package com.devsoft.myhotelapi.aop.handlers;

import com.devsoft.myhotelapi.exceptions.db.ResourceNotFoundException;
import com.devsoft.myhotelapi.validation.models.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ValidationError handleException(ResourceNotFoundException exception) {
        return new ValidationError(exception.getMessage());
    }
}
