package com.devsoft.myhotelapi.services;

import com.devsoft.myhotelapi.entities.City;
import com.devsoft.myhotelapi.services.base.IBaseService;
import com.devsoft.myhotelapi.services.base.Searchable;

public interface ICityService extends IBaseService<City, Long>, Searchable<City> {
    boolean existsCitiesByNameAndCountryId(String name, Long id);

    boolean existsCityByIdAndCountryId(Long id, Long countryId);
}
