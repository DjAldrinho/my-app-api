package com.devsoft.myhotelapi.controllers;

import com.devsoft.myhotelapi.builders.LocationBuilder;
import com.devsoft.myhotelapi.entities.City;
import com.devsoft.myhotelapi.entities.Country;
import com.devsoft.myhotelapi.enums.Tables;
import com.devsoft.myhotelapi.exceptions.db.ResourceExistsException;
import com.devsoft.myhotelapi.exceptions.db.ResourceNotFoundException;
import com.devsoft.myhotelapi.services.ICityService;
import com.devsoft.myhotelapi.services.ICountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("${api.base-api}/${api.api-version}/countries")
public class CountryController {

    private final ICountryService countryService;

    private final ICityService cityService;


    public CountryController(ICountryService countryService, ICityService cityService) {
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(this.countryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable("id") Long id) {

        Country country = this.getCountryModelById(id);

        return ResponseEntity.ok(country);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Country>> getCountryByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.countryService.searchByName(name));
    }

    @PostMapping
    public ResponseEntity<?> createCountry(@Valid @RequestBody Country newCountry) {

        boolean exists = this.countryService.findCountryByName(newCountry.getName()).isPresent();

        if (exists) {
            throw new ResourceExistsException(newCountry.getName(), Tables.COUNTRY.getValue());
        }

        Country country = this.countryService.save(newCountry);

        URI location = LocationBuilder.generateLocation(country.getId());

        return ResponseEntity.created(location).body(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable("id") Long id, @Valid @RequestBody Country newCountry) {

        Country country = this.getCountryModelById(id);

        boolean existsByName = this.countryService.findCountryByName(newCountry.getName()).isPresent();

        if (existsByName) {
            Country countryExistsByName = this.getCountryModelByName(newCountry.getName());
            if (!country.getId().equals(countryExistsByName.getId())) {
                throw new ResourceExistsException(newCountry.getName(), Tables.COUNTRY.getValue());
            }
        }

        country.setName(newCountry.getName());

        country = this.countryService.save(country);

        URI location = LocationBuilder.generateLocation(country.getId());

        return ResponseEntity.created(location).body(country);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable("id") Long id) {

        Country country = this.getCountryModelById(id);

        if (country != null) {
            this.countryService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.internalServerError().build();
    }


    @GetMapping("/{id}/cities")
    public ResponseEntity<Set<City>> getCities(@PathVariable("id") Long id) {

        Country country = this.getCountryModelById(id);

        return ResponseEntity.ok(country.getCities());
    }


    @PostMapping("/{id}/cities")
    public ResponseEntity<?> createCity(@PathVariable("id") Long id, @Valid @RequestBody City newCity) {

        boolean existsByName = this.cityService.existsCitiesByNameAndCountryId(newCity.getName(), id);

        if (existsByName) {
            throw new ResourceExistsException(newCity.getName(), Tables.CITY.getValue());
        }

        Country country = this.getCountryModelById(id);
        country.addCity(newCity);
        this.countryService.save(country);
        URI location = LocationBuilder.generateLocation(country.getId());
        return ResponseEntity.created(location).body(country);
    }

    @GetMapping("/{countryId}/cities/{id}")
    public ResponseEntity<?> getCity(@PathVariable("countryId") Long countryId, @PathVariable("id") Long id) {

        boolean exists = this.cityService.existsCityByIdAndCountryId(id, countryId);

        if (!exists) {
            throw new ResourceNotFoundException("The city not found in this country");
        }

        return ResponseEntity.ok(this.cityService.findOne(id));
    }

    @DeleteMapping("/{countryId}/cities/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("countryId") Long countryId, @PathVariable("id") Long id) {

        Country country = this.getCountryModelById(countryId);

        if (country != null) {
            City city = this.cityService.findOne(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "id", Tables.CITY.getValue()));

            this.cityService.delete(city.getId());
        }

        return ResponseEntity.noContent().build();
    }

    private Country getCountryModelById(Long id) {
        return this.countryService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "id", Tables.COUNTRY.getValue()));
    }

    private Country getCountryModelByName(String name) {
        return this.countryService.findCountryByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(name, "name", Tables.COUNTRY.getValue()));
    }
}
