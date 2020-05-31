package com.salon.command;

import com.salon.context.AppContext;
import com.salon.entity.User;
import com.salon.service.ProcedureService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChooseMasterCommand extends FrontCommand {

    private final ProcedureService procedureService;

    public ChooseMasterCommand() {
        this.procedureService = AppContext.getProcedureService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("masterId",req.getParameter("masterId"));
        req.setAttribute("procedures",procedureService.findAll("1"));
        req.setAttribute("pagesCount",procedureService.pagesCount());
        forward(user.getRole().name().toLowerCase(),"/procedure");
    }
}
