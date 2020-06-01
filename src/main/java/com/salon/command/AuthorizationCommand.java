package com.salon.command;

import com.salon.context.AppContext;
import com.salon.entity.User;
import com.salon.exception.ValidationException;
import com.salon.service.SalonService;
import com.salon.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class AuthorizationCommand extends FrontCommand {

  private final UserService userService;
  private final SalonService salonService;

  public AuthorizationCommand() {
    userService = AppContext.getUserService();
    salonService = AppContext.getSalonService();
  }

  @Override
  public void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    if (req.getParameter("loggingIn") != null) {
      login(req, resp);
    } else if (req.getParameter("registration") != null) {
      register(req, resp);
    } else {
      forward("authorization");
    }
  }

  private void login(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String username = req.getParameter("username");
    String password = req.getParameter("password");
    Optional<User> user = userService.login(username, password);
    if (user.isPresent()) {
      HttpSession session = req.getSession();
      session.setAttribute("user", userService.findByName(username));
      req.setAttribute("salons", salonService.findAll());
      forward("salons");
    } else {
      req.setAttribute("error", "wrong credentials");
      forward("authorization");
    }
  }

  private void register(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      User user = userService.register(req.getParameter("username"), req.getParameter("password"));
      req.getSession().setAttribute("user", user);
      req.setAttribute("salons", salonService.findAll());
      forward("/salons");
    } catch (ValidationException e) {
      req.setAttribute("error", e.getMessage());
      forward("authorization");
    }
  }
}
