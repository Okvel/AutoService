package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ShowDismissFormCommand implements BaseCommand {
    private static final String REQUEST_ATTRIBUTE_NAME_USERS = "users";
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        PagePath page = PagePath.DISMISS;
        UserService service = UserService.getInstance();
        ArrayList<User> users;
        try{
            users = service.findAll();
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_USERS, users);
        } catch (ServiceException ex){
            throw new CommandException(ex);
        }
        return page;
    }
}
