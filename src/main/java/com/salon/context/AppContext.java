package com.salon.context;

import com.salon.dao.*;
import com.salon.dao.impl.*;
import com.salon.service.ProcedureService;
import com.salon.service.SalonService;
import com.salon.service.UserService;
import com.salon.service.impl.ProcedureServiceImpl;
import com.salon.service.impl.SalonServiceImpl;
import com.salon.service.impl.UserServiceImpl;
import com.salon.service.util.DataParser;
import com.salon.service.util.PasswordUtil;
import com.salon.service.util.CredentialsValidator;

public class AppContext {

  private static final CommentDao COMMENT_DAO = new CommentDaoImpl();
  private static final RatingDao RATING_DAO = new RatingDaoImpl();
  private static final ReservationDao RESERVATION_DAO = new ReservationDaoImpl();
  private static final SalonDao SALON_DAO = new SalonDaoImpl();
  private static final UserDao USER_DAO = new UserDaoImpl();
  private static final ProcedureDao PROCEDURE_DAO = new ProcedureDaoImpl();
  private static final CredentialsValidator CREDENTIALS_VALIDATOR = new CredentialsValidator();
  private static final PasswordUtil PASSWORD_UTIL = new PasswordUtil();
  private static final DataParser DATA_PARSER = new DataParser();

  private static final UserService USER_SERVICE =
      new UserServiceImpl(USER_DAO, CREDENTIALS_VALIDATOR, PASSWORD_UTIL);
  private static final SalonService SALON_SERVICE =
      new SalonServiceImpl(SALON_DAO, USER_DAO, DATA_PARSER);
  private static final ProcedureService PROCEDURE_SERVICE =
      new ProcedureServiceImpl(PROCEDURE_DAO, DATA_PARSER);

  public static UserService getUserService() {
    return USER_SERVICE;
  }

  public static ProcedureService getProcedureService() {
    return PROCEDURE_SERVICE;
  }

  public static SalonService getSalonService() {
    return SALON_SERVICE;
  }
}
