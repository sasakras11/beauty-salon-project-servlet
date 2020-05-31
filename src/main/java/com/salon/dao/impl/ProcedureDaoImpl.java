package com.salon.dao.impl;

import com.salon.dao.ProcedureDao;
import com.salon.entity.Procedure;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProcedureDaoImpl extends AbstractCrudDaoImpl<Procedure> implements ProcedureDao {

  private static final String SAVE =
      "insert into procedures(name,description,duration_hours) values(?,?,?)";
  private static final String UPDATE =
      "update procedures set name = ?, description = ?, duration_hours = ? where procedure_id = ?";
  private static final String FIND_BY_ID = "select *from procedures where procedure_id = ?";
  private static final String GET_PAGE = "select * from procedures  limit ? offset ?";
  private static final String COUNT = "select count(*) as total from procedures";
  @Override
  protected void setStatementParams(PreparedStatement statement, Procedure entity)
      throws SQLException {
    statement.setString(1, entity.getName());
    statement.setString(2, entity.getDescription());
    statement.setInt(3, entity.getDurationHours());
  }

  @Override
  protected void setStatementParamsWithId(PreparedStatement statement, Procedure entity)
      throws SQLException {
    setStatementParams(statement, entity);
    statement.setInt(4, entity.getId());
  }

  @Override
  protected Procedure mapResultSetToEntity(ResultSet resultSet) throws SQLException {
    return Procedure.ProcedureBuilder.aProcedure()
        .withId(resultSet.getInt("procedure_id"))
        .withName(resultSet.getString("name"))
        .withDescription(resultSet.getString("description"))
        .withDurationHours(resultSet.getInt("duration_hours"))
        .build();
  }

  @Override
  public void save(Procedure entity) {
    save(entity, SAVE);
  }

  @Override
  public Optional<Procedure> findById(Integer id) {
    return findByParam(id,FIND_BY_ID,SET_STATEMENT_INT_PARAM) ;
  }

  @Override
  public void update(Procedure entity) {
    update(entity, UPDATE);
  }

  @Override
  public void deleteById(Integer id) {throw new UnsupportedOperationException();
  }

  @Override
  public int count() {
    return count(COUNT);
  }

  @Override
  public List<Procedure> findAll(int page, int itemsPerPage) {
    return findAll(page,itemsPerPage,GET_PAGE);
  }
}
