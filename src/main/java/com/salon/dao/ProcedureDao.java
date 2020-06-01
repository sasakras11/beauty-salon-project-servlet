package com.salon.dao;

import com.salon.entity.Procedure;

public interface ProcedureDao extends CrudPageableDao<Procedure> {

  int count();
}
