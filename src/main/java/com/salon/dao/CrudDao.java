package com.salon.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface CrudDao<E> {

    void save(E entity);

    Optional<E> findById(Integer id);

    default List<E> findAll() {
        return Collections.emptyList();
    }

    void update(E entity);

    void deleteById(Integer id);
}