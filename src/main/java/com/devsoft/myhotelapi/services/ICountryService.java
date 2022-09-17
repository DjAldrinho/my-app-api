package com.devsoft.myhotelapi.services;

import com.devsoft.myhotelapi.entities.Country;
import com.devsoft.myhotelapi.services.base.IBaseService;
import com.devsoft.myhotelapi.services.base.Searchable;

import java.util.Optional;

public interface ICountryService extends IBaseService<Country, Long>, Searchable<Country> {
    Optional<Country> findCountryByName(String name);

}
