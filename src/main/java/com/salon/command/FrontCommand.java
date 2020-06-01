package com.salon.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
  protected ServletContext context;
  protected HttpServletRequest request;
  protected HttpServletResponse response;

  public void init(
      ServletContext servletContext,
      HttpServletRequest servletRequest,
      HttpServletResponse servletResponse) {
    this.context = servletContext;
    this.request = servletRequest;
    this.response = servletResponse;
  }

  public abstract void process(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException;

  protected void forward(String role, String target) throws ServletException, IOException {
    target = String.format("/pages/%s/%s.jsp", role, target);
    RequestDispatcher dispatcher = context.getRequestDispatcher(target);
    dispatcher.forward(request, response);
  }

  protected void forward(String target) throws ServletException, IOException {
    target = String.format("/pages/%s.jsp", target);
    RequestDispatcher dispatcher = context.getRequestDispatcher(target);
    dispatcher.forward(request, response);
  }
}
