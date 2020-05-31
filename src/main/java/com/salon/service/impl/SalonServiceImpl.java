package com.salon.service.impl;

import com.salon.dao.SalonDao;
import com.salon.dao.UserDao;
import com.salon.entity.Salon;
import com.salon.entity.User;
import com.salon.exception.ValidationException;
import com.salon.service.SalonService;
import com.salon.service.util.DataParser;

import java.util.List;

public class SalonServiceImpl implements SalonService {

  private final SalonDao salonDao;
  private final UserDao userDao;
  private final DataParser dataParser;

  public SalonServiceImpl(SalonDao salonDao, UserDao userDao, DataParser dataParser) {
    this.salonDao = salonDao;
    this.userDao = userDao;
    this.dataParser = dataParser;
  }

  @Override
  public List<Salon> findAll() {
    return salonDao.findAll();
  }

  @Override
  public List<User> getMastersOfSalon(String salonId) {
    return dataParser
        .parseInt(salonId)
        .map(userDao::findAllMasterBySalonId)
        .orElseThrow(
            () ->
                new ValidationException(
                    String.format("request param salonId - [%s] is noy valid", salonId)));
  }
}
