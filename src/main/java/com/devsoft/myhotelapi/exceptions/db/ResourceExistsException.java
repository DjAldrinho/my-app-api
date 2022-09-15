package com.devsoft.myhotelapi.exceptions.db;


public class ResourceExistsException extends RuntimeException {
    public ResourceExistsException(String name, String table) {
        super(String.format("The resource %s exists in %s", name, table));
    }
}
