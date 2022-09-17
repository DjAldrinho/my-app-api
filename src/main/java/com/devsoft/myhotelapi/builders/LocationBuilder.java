package com.devsoft.myhotelapi.builders;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class LocationBuilder {

    public static URI generateLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(id).toUri();
    }

}
