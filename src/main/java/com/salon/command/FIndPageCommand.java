package com.salon.command;

import com.salon.context.AppContext;
import com.salon.entity.User;
import com.salon.service.ProcedureService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FIndPageCommand extends FrontCommand {

    private final ProcedureService procedureService;

    public FIndPageCommand() {
        this.procedureService = AppContext.getProcedureService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
       req.setAttribute("procedures",procedureService.findAll(req.getParameter("page")));
        req.setAttribute("pagesCount",procedureService.pagesCount());

        forward(user.getRole().name().toLowerCase(),"procedure");

    }
}
