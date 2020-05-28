package com.salon.dao.impl;

import com.salon.dao.RatingDao;
import com.salon.entity.Rating;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RatingDaoImpl extends AbstractCrudDaoImpl<Rating> implements RatingDao {

  private static final String SAVE = "insert into ratings(rating_mark,master_id) values(?,?)";
  private static final String UPDATE =
      "update ratings set rating_mark = ?,master_id = ? where rating_id = ?";
  private static final String FIND_BY_ID = "select *from ratings where rating_id = ?";

  @Override
  protected void setStatementParams(PreparedStatement statement, Rating entity)
      throws SQLException {
    statement.setDouble(1, entity.getRatingMark());
    statement.setInt(2, entity.getMaster().getId());
  }

  @Override
  protected void setStatementParamsWithId(PreparedStatement statement, Rating entity)
      throws SQLException {
    setStatementParams(statement, entity);
    statement.setInt(3, entity.getId());
    
  }

  @Override
  protected Rating mapResultSetToEntity(ResultSet resultSet) throws SQLException {
    return Rating.RatingBuilder.aRating()
        .withId(resultSet.getInt("rating_id"))
        .withRatingMark(resultSet.getDouble("rating_mark"))
        .build();
  }

  @Override
  public void save(Rating entity) {
    save(entity, SAVE);
  }

  @Override
  public Optional<Rating> findById(Integer id) {
    return findByParam(id, FIND_BY_ID, SET_STATEMENT_INT_PARAM);
  }

  @Override
  public void update(Rating entity) {
    update(entity, UPDATE);
  }

  @Override
  public void deleteById(Integer id) {
    throw new UnsupportedOperationException();
  }
}
