package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ShowUsersCommand implements BaseCommand {
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        try {
            UserService service = UserService.getInstance();
            ArrayList<User> users = service.findAll();
            request.setAttribute("users", users);
        } catch (ServiceException ex) {
            throw new CommandException(ex);
        }
        return PagePath.USERS_PAGE;
    }
}
