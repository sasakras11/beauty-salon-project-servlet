package com.salon;

import com.salon.context.AppContext;
import com.salon.service.ProcedureService;
import com.salon.service.impl.ProcedureServiceImpl;

public class Main {
  public static void main(String[] args) throws Exception {

    ProcedureService procedureService = AppContext.getProcedureService();

    System.out.println(procedureService.findAll("2"));
  }
}
