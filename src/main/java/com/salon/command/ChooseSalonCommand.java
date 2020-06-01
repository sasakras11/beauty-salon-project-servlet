package com.salon.command;

import com.salon.context.AppContext;
import com.salon.entity.User;
import com.salon.service.SalonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChooseSalonCommand extends FrontCommand {

  private static final String SALON_ID = "salonId";
  private final SalonService salonService;

  public ChooseSalonCommand() {
    this.salonService = AppContext.getSalonService();
  }

  @Override
  public void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    User user = (User) req.getSession().getAttribute("user");
    req.setAttribute("masters", salonService.getMastersOfSalon(req.getParameter(SALON_ID)));
    req.getSession().setAttribute(SALON_ID, req.getParameter(SALON_ID));
    forward(user.getRole().name().toLowerCase() + "/masters");
  }
}
