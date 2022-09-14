package com.devsoft.myhotelapi.services;

import com.devsoft.myhotelapi.models.Country;
import com.devsoft.myhotelapi.repositories.ICountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {

    private final ICountryRepository countryRepository;

    public CountryServiceImpl(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return (List<Country>) this.countryRepository.findAll();
    }

    @Override
    @Transactional
    public Country save(Country country) {
        return this.countryRepository.save(country);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Country> findOne(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.countryRepository.deleteById(id);
    }
}
