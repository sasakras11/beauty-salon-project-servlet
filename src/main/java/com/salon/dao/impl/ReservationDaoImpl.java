package com.salon.dao.impl;

import com.salon.dao.ReservationDao;
import com.salon.entity.Procedure;
import com.salon.entity.Reservation;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl extends AbstractCrudDaoImpl<Reservation> implements ReservationDao {

    private static final String SAVE = "insert into reservations(end_hour,procedure_id,start_hour,beauty_master_user_id,client_user_id) values(?,?,?,?,?)";
    private static final String UPDATE = "update reservations set end_hour = ?,procedure_id= ?,start_hour = ?,beauty_master_user_id = ?,client_user_id = ? where reservation_id = ?";
    private static final String FIND_MASTER_RESERVATIONS = "select *from reservations where beauty_master_user_id = ?";
    private static final String FIND_BY_ID = "select *from reservations where reservation_id = ?";
    @Override
    protected void setStatementParams(PreparedStatement statement, Reservation entity) throws SQLException {
        statement.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getEnd()));
        statement.setInt(2,entity.getProcedure().getId());
        statement.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getStart()));
        statement.setInt(4,entity.getBeautyMaster().getId());
        statement.setInt(5,entity.getClient().getId());
    }

    @Override
    protected void setStatementParamsWithId(PreparedStatement statement, Reservation entity) throws SQLException {
                          setStatementParams(statement,entity);
                          statement.setInt(6,entity.getId());
    }

    @Override
    public List<Reservation> findMasterReservations(int masterId) {

        return findListByParam(masterId,FIND_MASTER_RESERVATIONS,SET_STATEMENT_INT_PARAM);
    }



    @Override
    protected Reservation mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Reservation.ReservationBuilder.aReservation()
                .withId(resultSet.getInt("reservation_id"))
                .withEnd(resultSet.getDate("end_hour"))
                .withStart(resultSet.getDate("start_hour"))
                .build();
    }

    @Override
    public void save(Reservation entity) {
                    save(entity,SAVE);
    }

    @Override
    public Optional<Reservation> findById(Integer id) {
        return findByParam(id,FIND_BY_ID,SET_STATEMENT_INT_PARAM);
    }

    @Override
    public void update(Reservation entity) {
        update(entity,UPDATE);
    }

    @Override
    public void deleteById(Integer id) {
             throw new UnsupportedOperationException();
    }
}
