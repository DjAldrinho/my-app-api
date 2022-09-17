package com.devsoft.myhotelapi.services;

import com.devsoft.myhotelapi.entities.Place;
import com.devsoft.myhotelapi.repositories.IPlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements IPlaceService {

    private final IPlaceRepository placeRepository;

    public PlaceServiceImpl(IPlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Place> findAll() {
        return (List<Place>) this.placeRepository.findAll();
    }

    @Override
    @Transactional
    public Place save(Place place) {
        return this.placeRepository.save(place);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Place> findOne(Long id) {
        return this.placeRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.placeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Place> searchByName(String name) {
        return this.placeRepository.findPlacesByNameContainingIgnoreCaseOrAddressContainingIgnoreCaseOrCityName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Place> findPlacesByType(String type) {
        return this.placeRepository.findPlacesByType(type);
    }

}
