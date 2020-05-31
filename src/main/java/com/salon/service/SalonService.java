package com.salon.service;

import com.salon.entity.Salon;
import com.salon.entity.User;

import java.util.List;

public interface SalonService {

    List<Salon> findAll();

    List<User> getMastersOfSalon(String salonId);
}
