package com.devsoft.myhotelapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors;
    private final String message;

    private final Date timestamp;

    public ValidationError(String message) {
        this.message = message;
        this.timestamp = new Date();
    }

    public void addError(String error) {
        errors.add(error);
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
