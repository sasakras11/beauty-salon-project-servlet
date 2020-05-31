package com.salon.dao.impl;

import com.salon.dao.SalonDao;
import com.salon.entity.Salon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SalonDaoImpl extends AbstractCrudDaoImpl<Salon> implements SalonDao {

  private static final String SAVE = "insert into salons(address) values (?)";
  private static final String UPDATE = "update salons set address = ? where salon_id = ?";
  private static final String FIND_BY_ID = "select *from salons where salon_id = ?";
  private static final String FIND_BY_ADDRESS = "select *from salons where address = ?";
  private static final String FIND_ALL = "select *from salons";

  @Override
  protected void setStatementParams(PreparedStatement statement, Salon entity) throws SQLException {
    statement.setString(1, entity.getAddress());
  }

  @Override
  protected void setStatementParamsWithId(PreparedStatement statement, Salon entity)
      throws SQLException {
    setStatementParams(statement, entity);
    statement.setInt(2, entity.getId());
  }

  @Override
  protected Salon mapResultSetToEntity(ResultSet resultSet) throws SQLException {
    return Salon.SalonBuilder.aSalon()
        .withId(resultSet.getInt("salon_id"))
        .withAddress(resultSet.getString("address"))
        .build();
  }

  @Override
  public void save(Salon entity) {
    save(entity,SAVE);
  }

  @Override
  public Optional<Salon> findById(Integer id) {
    return findByParam(id,FIND_BY_ID,SET_STATEMENT_INT_PARAM);
  }

  @Override
  public void update(Salon entity) {
    update(entity,UPDATE);
  }

  @Override
  public void deleteById(Integer id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<Salon> findByAddress(String address) {
    return  findByParam(address,FIND_BY_ADDRESS,SET_STATEMENT_STRING_PARAM);
  }

  @Override
  public List<Salon> findAll() {
    return findAll(FIND_ALL);
  }
}
