package com.salon.dao;

import com.salon.entity.Comment;
import com.salon.entity.User;

import java.util.List;

public interface CommentDao extends CrudDao<Comment> {

  List<Comment> findMasterComments(int masterId);
}
