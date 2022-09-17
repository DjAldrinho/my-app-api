package com.devsoft.myhotelapi.repositories;

import com.devsoft.myhotelapi.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICountryRepository extends CrudRepository<Country, Long> {
    Optional<Country> findCountryByName(String name);

    List<Country> findByNameContainingIgnoreCase(String name);
}
