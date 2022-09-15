package com.devsoft.myhotelapi.helpers;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class LocationHelper {

    public static URI getLocation(Long id) {
        if (id != null) {
            return ServletUriComponentsBuilder.fromCurrentRequest().
                    path("/{id}")
                    .buildAndExpand(id).toUri();
        }

        return null;
    }

}
