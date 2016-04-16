package by.bsuir.spp.autoservice.controller;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.CommandHelper;
import by.bsuir.spp.autoservice.command.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = "*.do", loadOnStartup = 0)
public class Controller extends HttpServlet {
    private static final String ATTRIBUTE_NAME_COMMAND = "command";

    private static Logger logger = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = (String) request.getAttribute(ATTRIBUTE_NAME_COMMAND);
        BaseCommand command = CommandHelper.valueOf(commandName).getCommand();
        try {
            PagePath path = command.execute(request);
            request.getRequestDispatcher(path.getPage()).forward(request, response);
        } catch (CommandException ex) {
            logger.error("Command execute error", ex);
        }
    }
}
