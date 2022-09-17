package com.devsoft.myhotelapi.services;


import com.devsoft.myhotelapi.entities.Place;
import com.devsoft.myhotelapi.services.base.IBaseService;
import com.devsoft.myhotelapi.services.base.Searchable;

import java.util.List;

public interface IPlaceService extends IBaseService<Place, Long>, Searchable<Place> {
    List<Place> findPlacesByType(String name);

}
