package com.devsoft.myhotelapi.repositories;

import com.devsoft.myhotelapi.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ICountryRepository extends CrudRepository<Country, Long> {
}
