package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements BaseCommand {
    private static final String PARAMETER_NAME_LOGIN = "login";
    private static final String PARAMETER_NAME_PASSWORD = "password";
    private static final String PARAMETER_NAME_FIRST_NAME = "first_name";
    private static final String PARAMETER_NAME_LAST_NAME = "last_name";
    private static final String PARAMETER_NAME_PHONE = "phone";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;

        Credentials credentials = new Credentials();
        credentials.setLogin(request.getParameter(PARAMETER_NAME_LOGIN));
        credentials.setPassword(request.getParameter(PARAMETER_NAME_PASSWORD));
        User user = new User();
        user.setCredentials(credentials);
        Person personInfo = new Person();
        personInfo.setFirstName(request.getParameter(PARAMETER_NAME_FIRST_NAME));
        personInfo.setLastName(request.getParameter(PARAMETER_NAME_LAST_NAME));
        personInfo.setPhoneNumber(request.getParameter(PARAMETER_NAME_PHONE));
        user.setPersonInfo(personInfo);
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

        return page;
    }
}
