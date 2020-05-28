package com.salon.dao.impl;

import com.salon.dao.CommentDao;
import com.salon.entity.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class CommentDaoImpl extends AbstractCrudDaoImpl<Comment> implements CommentDao {

  private static final String FIND_BY_ID = "select *from comments where comment_id = ?";
  private static final String SAVE =
      "insert into comments (comment_text,comment_date,service_mark,commentator_id,master_id) values(?,?,?,?,?)";
  private static final String UPDATE =
      "update comments set  comment_text = ?,comment_date = ?,service_mark = ?,commentator_id = ? ,master_id = ? where comment_id = ?";

  private static final String FIND_MASTER_COMMENTS = "select *from comments where master_id = ?";

  @Override
  protected void setStatementParams(PreparedStatement statement, Comment entity)
      throws SQLException {
    statement.setString(1, entity.getCommentText());
    statement.setString(
        2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getCommentDate()));
    statement.setInt(3, entity.getServiceMark());
    statement.setInt(4, entity.getCommentator().getId());
    statement.setInt(5, entity.getMaster().getId());
  }

  @Override
  protected void setStatementParamsWithId(PreparedStatement statement, Comment entity)
      throws SQLException {
    setStatementParams(statement, entity);
    statement.setInt(6, entity.getId());
  }

  @Override
  protected Comment mapResultSetToEntity(ResultSet resultSet) throws SQLException {
    return Comment.CommentBuilder.aComment()
        .withId(resultSet.getInt("comment_id"))
        .withCommentDate(resultSet.getDate("comment_Date"))
        .withServiceMark(resultSet.getInt("service_mark"))
        .withCommentText(resultSet.getString("comment_text"))
        .build();
  }

  @Override
  public void save(Comment entity) {
    save(entity, SAVE);
  }

  @Override
  public Optional<Comment> findById(Integer id) {
    return findByParam(id, FIND_BY_ID, SET_STATEMENT_INT_PARAM);
  }

  @Override
  public void update(Comment entity) {
    update(entity, UPDATE);
  }

  @Override
  public void deleteById(Integer id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Comment> findMasterComments(int masterId) {
    return findListByParam(masterId, FIND_MASTER_COMMENTS, SET_STATEMENT_INT_PARAM);
  }
}
