package com.salon.dao;

import com.salon.entity.Reservation;
import com.salon.entity.User;

import java.util.List;

public interface ReservationDao extends CrudDao<Reservation> {

  List<Reservation> findMasterReservations(
      int masterId);
}
