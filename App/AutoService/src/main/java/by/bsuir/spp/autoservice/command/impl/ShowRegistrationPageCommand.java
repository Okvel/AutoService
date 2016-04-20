package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.UserRole;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserRoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowRegistrationPageCommand implements BaseCommand {
    private static final String REQUEST_ATTRIBUTE_NAME_ROLE_LIST = "roles";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        List<UserRole> roles;
        try {
            UserRoleService service = UserRoleService.getInstance();
            roles = service.findAll();
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_ROLE_LIST, roles);
        } catch (ServiceException ex) {
            throw new CommandException(ex);
        }

        return null;
    }
}
