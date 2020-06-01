package com.salon.service.impl;

import com.salon.dao.ProcedureDao;
import com.salon.entity.Procedure;
import com.salon.service.ProcedureService;
import com.salon.service.util.DataParser;

import java.util.List;

public class ProcedureServiceImpl implements ProcedureService {

  private static final int ITEMS_PER_PAGE = 3;
  private final ProcedureDao procedureDao;
  private final DataParser dataParser;

  public ProcedureServiceImpl(ProcedureDao procedureDao, DataParser dataParser) {
    this.procedureDao = procedureDao;
    this.dataParser = dataParser;
  }

  @Override
  public List<Procedure> findAll(String page) {

    return dataParser
        .parseInt(page)
        .filter(procedurePage -> procedurePage > 0 && procedurePage <= pagesCount())
        .map(procedurePage -> procedureDao.findAll(procedurePage, ITEMS_PER_PAGE))
        .orElse(procedureDao.findAll(1, ITEMS_PER_PAGE));
  }

  @Override
  public int pagesCount() {
    int proceduresCount = procedureDao.count();
    return proceduresCount % 2 == 0 ? proceduresCount / 3 : proceduresCount / 3 + 1;
  }
}
