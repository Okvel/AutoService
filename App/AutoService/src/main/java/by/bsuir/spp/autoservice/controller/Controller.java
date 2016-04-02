package by.bsuir.spp.autoservice.controller;

import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.CommandHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = "*.do", loadOnStartup = 0)
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getRequestDispatcher(CommandHelper.valueOf((String) req.getAttribute("command")).getCommand().execute(req))
            .forward(req, resp);
        } catch (CommandException ex) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
