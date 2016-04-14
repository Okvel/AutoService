package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.util.RegistrationValidator;
import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;

        User user = RegistrationValidator.validate(request);
        if (user != null) {
            try {
                UserService service = UserService.getInstance();
                if (service.save(user)) {
                    page = "some_page.jsp";
                } else {
                    page = "another_page.jsp";
                }
            } catch (ServiceException ex) {
                throw new CommandException(ex);
            }
        } else {
            page = "error_page.jsp";
        }

        return page;
    }
}
