package com.salon.service;

import com.salon.entity.Procedure;

import java.util.List;

public interface ProcedureService {

    List<Procedure> findAll(String page);

    int  pagesCount();
}
