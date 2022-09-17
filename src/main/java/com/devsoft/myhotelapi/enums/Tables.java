package com.devsoft.myhotelapi.enums;

import lombok.Getter;

@Getter
public enum Tables {

    CITY("city"), COUNTRY("country");

    private final String value;

    Tables(String value) {
        this.value = value;
    }
}
