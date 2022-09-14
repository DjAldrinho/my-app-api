package com.devsoft.myhotelapi.services.base;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T, ID> {

    List<T> findAll();

    T save(T model);

    Optional<T> findOne(ID id);

    void delete(ID id);
}
