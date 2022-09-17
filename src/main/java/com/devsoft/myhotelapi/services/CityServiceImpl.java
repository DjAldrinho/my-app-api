package com.devsoft.myhotelapi.services;

import com.devsoft.myhotelapi.entities.City;
import com.devsoft.myhotelapi.repositories.ICityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    private final ICityRepository cityRepository;

    public CityServiceImpl(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> findAll() {
        return (List<City>) this.cityRepository.findAll();
    }

    @Override
    @Transactional
    public City save(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<City> findOne(Long id) {
        return this.cityRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.cityRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<City> searchByName(String name) {
        return this.cityRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsCitiesByNameAndCountryId(String name, Long id) {
        return this.cityRepository.existsCitiesByNameAndCountryId(name, id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsCityByIdAndCountryId(Long id, Long countryId) {
        return this.cityRepository.existsCityByIdAndCountryId(id, countryId);
    }
}
