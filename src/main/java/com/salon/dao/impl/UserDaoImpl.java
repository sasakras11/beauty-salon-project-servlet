package com.salon.dao.impl;

import com.salon.dao.UserDao;
import com.salon.entity.Role;
import com.salon.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDaoImpl<User> implements UserDao {

  private static final String SAVE= "insert into users(username,password,role) VALUES(?,?,?)";
  private static final String FIND_BY_ID = "select *from users where user_id = ?";
  private static final String UPDATE =
      "update users set username = ?,password = ?,role = ? where user_id=?";
  private static final String FIND_BY_USERNAME = "select *from users where username = ?";
  private static final String FIND_MASTERS_OF_SALON =
      "select user_id,username,password,role from users as u inner join salons_masters as sm inner join salons as s on u.user_id = sm.masters_user_id and s.salon_id = sm.salon_id where s.salon_id = ?";

  @Override
  protected void setStatementParams(PreparedStatement statement, User entity) throws SQLException {
    statement.setString(1, entity.getUsername());
    statement.setString(2, entity.getPassword());
    statement.setString(3, entity.getRole().toString());
  }

  @Override
  protected void setStatementParamsWithId(PreparedStatement statement, User entity)
      throws SQLException {
    setStatementParams(statement, entity);
    statement.setInt(4, entity.getId());
  }

  @Override
  protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
    return User.UserBuilder.anUser()
            .withId(resultSet.getInt("user_id"))
            .withUsername(resultSet.getString("username"))
            .withPassword(resultSet.getString("password"))
            .withRole(Role.valueOf(resultSet.getString("role")))
        .build();
  }

  @Override
  public void save(User entity) {
    save(entity, SAVE);
  }

  @Override
  public Optional<User> findById(Integer id) {
    return findByParam(id, FIND_BY_ID, SET_STATEMENT_INT_PARAM);
  }

  @Override
  public void update(User entity) {
    update(entity, UPDATE);
  }

  @Override
  public void deleteById(Integer id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Optional<User> findByUserName(String username) {
    return findByParam(username, FIND_BY_USERNAME, SET_STATEMENT_STRING_PARAM);
  }

  @Override
  public List<User> findAllMasterBySalonId(int salonId) {
    return findListByParam(salonId, FIND_MASTERS_OF_SALON, SET_STATEMENT_INT_PARAM);
  }
}
