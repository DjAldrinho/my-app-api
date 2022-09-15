package com.devsoft.myhotelapi.services;

import com.devsoft.myhotelapi.models.Country;
import com.devsoft.myhotelapi.services.base.IBaseService;

import java.util.List;
import java.util.Optional;

public interface ICountryService extends IBaseService<Country, Long> {
    Optional<Country> findCountryByName(String name);

    List<Country> searchCountriesByName(String  name);
}
