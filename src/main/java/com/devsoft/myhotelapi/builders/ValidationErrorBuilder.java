package com.devsoft.myhotelapi.builders;

import com.devsoft.myhotelapi.models.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ValidationErrorBuilder {


    public static ResponseEntity<Map<String, Object>> getValidationResponse(String message, List<String> errors,
                                                                            HttpStatus status) {
        ValidationError validationError = new ValidationError(message);

        if (!errors.isEmpty()) {
            validationError.setErrors(errors);
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", validationError.getTimestamp());
        body.put("message", validationError.getMessage());
        body.put("errors", validationError.getErrors());

        return new ResponseEntity<>(body, status);
    }
}
