package com.devsoft.myhotelapi.repositories;

import com.devsoft.myhotelapi.entities.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityRepository extends CrudRepository<City, Long> {

    boolean existsCitiesByNameAndCountryId(String name, Long id);

    boolean existsCityByIdAndCountryId(Long id, Long countryId);

    List<City> findByNameContainingIgnoreCase(String name);

}
