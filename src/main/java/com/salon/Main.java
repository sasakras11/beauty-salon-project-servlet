package com.salon;


import com.salon.dao.CommentDao;
import com.salon.dao.RatingDao;
import com.salon.dao.ReservationDao;
import com.salon.dao.SalonDao;
import com.salon.dao.impl.CommentDaoImpl;
import com.salon.dao.impl.RatingDaoImpl;
import com.salon.dao.impl.ReservationDaoImpl;
import com.salon.dao.impl.SalonDaoImpl;
import com.salon.entity.*;
import java.util.Date;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception{

    ReservationDao reservationDao = new ReservationDaoImpl();

    User client = User.UserBuilder.anUser().withId(1).build();
    User master  = User.UserBuilder.anUser().withId(3).build();

    Reservation reservation1 = Reservation.ReservationBuilder.aReservation()
            .withProcedure(Procedure.HAIR_CUT)
            .withStart(new Date())
            .withEnd(new Date())
            .withClient(client)
            .withBeautyMaster(master)
            .build();

    System.out.println(reservationDao.findById(1));
    reservationDao.save(reservation1);

    Reservation reservation2 = Reservation.ReservationBuilder.aReservation()
            .withId(1)
            .withProcedure(Procedure.HAIR_CUT)
            .withStart(new Date())
            .withEnd(new Date())
            .withClient(client)
            .withBeautyMaster(master)
            .build();

    reservationDao.update(reservation2);

    List<Reservation> master_reservations =   reservationDao.findMasterReservations(3);

    for (Reservation re : master_reservations) {
      System.out.println(re);
    }

  }

}
