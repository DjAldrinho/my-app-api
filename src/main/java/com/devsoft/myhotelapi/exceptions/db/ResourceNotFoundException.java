package com.devsoft.myhotelapi.exceptions.db;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id, String table) {
        super("Resource not found for id: " + id + " in " + table);
    }
}
