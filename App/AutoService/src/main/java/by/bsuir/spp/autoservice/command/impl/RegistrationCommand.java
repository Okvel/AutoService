package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.command.util.RegistrationValidator;
import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_TEXT = "text";
    private static final String SUCCESS_MESSAGE = "Employee was successfully registered";
    private static final String FAIL_MESSAGE = "Employee wasn't registered";
    private static final String ERROR_MESSAGE = "Some fields contains wrong information";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        User user = RegistrationValidator.validate(request);
        if (user != null) {
            try {
                UserService service = UserService.getInstance();
                if (service.save(user)) {
                    request.setAttribute(REQUEST_PARAMETER_TEXT, SUCCESS_MESSAGE);
                } else {
                    request.setAttribute(REQUEST_PARAMETER_TEXT, FAIL_MESSAGE);
                }
            } catch (ServiceException ex) {
                throw new CommandException(ex);
            }
        } else {
            request.setAttribute(REQUEST_PARAMETER_TEXT, ERROR_MESSAGE);
        }

        return PagePath.MESSAGE;
    }
}
