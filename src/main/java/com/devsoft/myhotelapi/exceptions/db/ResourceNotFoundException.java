package com.devsoft.myhotelapi.exceptions.db;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String name, String fieldName, String table) {
        super(String.format("The resource for %s: %s not found in %s", fieldName, name, table));
    }
}
