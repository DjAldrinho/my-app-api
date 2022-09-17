package com.devsoft.myhotelapi.repositories;

import com.devsoft.myhotelapi.entities.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Repository
public interface IPlaceRepository extends CrudRepository<Place, Long> {

    List<Place> findPlacesByType(String type);

    List<Place> findPlacesByNameContainingIgnoreCaseOrAddressContainingIgnoreCaseOrCityName(String search);
}
