package com.devsoft.myhotelapi.controllers;

import com.devsoft.myhotelapi.builders.LocationBuilder;
import com.devsoft.myhotelapi.enums.Tables;
import com.devsoft.myhotelapi.exceptions.db.ResourceExistsException;
import com.devsoft.myhotelapi.exceptions.db.ResourceNotFoundException;
import com.devsoft.myhotelapi.entities.City;
import com.devsoft.myhotelapi.entities.Country;
import com.devsoft.myhotelapi.services.ICityService;
import com.devsoft.myhotelapi.services.ICountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.base-api}/${api.api-version}/cities")
public class CityController {


    private final ICityService cityService;

    private final ICountryService countryService;


    public CityController(ICityService cityService, ICountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getCities() {
        return ResponseEntity.ok(this.cityService.findAll());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCity(@PathVariable("id") Long id,
                                        @Valid @RequestBody City newCity) {

        City city = this.getCityModelById(id);

        Country country = this.countryService.findOne(newCity.getCountry().getId())
                .orElseThrow(() -> new ResourceNotFoundException(newCity.getCountry().getId().toString()
                        , "id", Tables.COUNTRY.getValue()));


        boolean existInCountry = this.cityService.existsCitiesByNameAndCountryId(newCity.getName(), country.getId());

        if (existInCountry) {
            throw new ResourceExistsException(newCity.getName(), Tables.COUNTRY.getValue());
        }

        country.removeCity(city);

        city.setName(newCity.getName());
        city = this.cityService.save(city);

        country.addCity(city);
        this.countryService.save(country);

        URI location = LocationBuilder.generateLocation(city.getId());

        return ResponseEntity.created(location).body(city);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<City>> getCityByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.cityService.searchByName(name));
    }


    private City getCityModelById(Long id) {
        return this.cityService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "id", Tables.CITY.getValue()));
    }
}
