package com.devsoft.myhotelapi.controllers;

import com.devsoft.myhotelapi.exceptions.db.ResourceNotFoundException;
import com.devsoft.myhotelapi.models.Country;
import com.devsoft.myhotelapi.services.ICountryService;
import com.devsoft.myhotelapi.validation.ValidationErrorBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.base-api}/${api.api-version}/countries")
public class CountryController {

    private final ICountryService countryService;

    public CountryController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(this.countryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable("id") Long id) {
        Country country = this.countryService.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "country"));

        return ResponseEntity.ok(country);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createCountry(@Valid @RequestBody Country country, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Country result = this.countryService.save(country);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(result);

    }
}
