package com.salon.dao;

import com.salon.entity.Reservation;
import com.salon.entity.User;

import java.util.List;

public interface ReservationDao extends CrudDao<Reservation>{

    List<Reservation> findMasterReservations(int masterId);    // TODO rename reservation class(session, beauty session etc.) or method
}
