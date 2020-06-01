package com.salon.dao;

import com.salon.entity.Salon;

import java.util.Optional;

public interface SalonDao extends CrudDao<Salon> {

  Optional<Salon> findByAddress(String address);
}
