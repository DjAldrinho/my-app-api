package com.devsoft.myhotelapi.controllers;

import com.devsoft.myhotelapi.exceptions.db.ResourceExistsException;
import com.devsoft.myhotelapi.exceptions.db.ResourceNotFoundException;
import com.devsoft.myhotelapi.helpers.LocationHelper;
import com.devsoft.myhotelapi.models.Country;
import com.devsoft.myhotelapi.services.ICountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.base-api}/${api.api-version}/countries")
public class CountryController {

    private final ICountryService countryService;

    private static final String TABLE = "country";

    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
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
        return ResponseEntity.ok(this.countryService.searchCountriesByName(name));
    }

    @PostMapping
    public ResponseEntity<?> createCountry(@Valid @RequestBody Country newCountry) {

        boolean exists = this.existsCountryByName(newCountry.getName());

        if (exists) {
            throw new ResourceExistsException(newCountry.getName(), TABLE);
        }

        Country country = this.countryService.save(newCountry);

        URI location = LocationHelper.getLocation(country.getId());

        return ResponseEntity.created(location).body(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable("id") Long id, @Valid @RequestBody Country newCountry) {

        Country country = this.getCountryModelById(id);

        boolean existsByName = this.existsCountryByName(newCountry.getName());

        if (existsByName) {
            Country countryExistsByName = this.getCountryModelByName(newCountry.getName());
            if (!country.getId().equals(countryExistsByName.getId())) {
                throw new ResourceExistsException(newCountry.getName(), TABLE);
            }
        }

        country.setName(newCountry.getName());

        country = this.countryService.save(country);

        URI location = LocationHelper.getLocation(country.getId());

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

    private Country getCountryModelById(Long id) {
        return this.countryService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString(), "id", TABLE));
    }

    private Country getCountryModelByName(String name) {
        return this.countryService.findCountryByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(name, "name", TABLE));
    }

    private boolean existsCountryByName(String name) {
        return this.countryService.findCountryByName(name).isPresent();
    }

    private boolean existsCountryById(Long id) {
        return this.countryService.findOne(id).isPresent();
    }
}
