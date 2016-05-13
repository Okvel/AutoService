package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand implements BaseCommand {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static final String SESSION_ATTRIBUTE_ROLE = "role";
    private static final String PARAMETER_NAME_LOGIN = "login";
    private static final String PARAMETER_NAME_PASSWORD = "password";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        PagePath page = PagePath.LOGIN;
        User user;
        Credentials credentials = new Credentials();
        credentials.setLogin(request.getParameter(PARAMETER_NAME_LOGIN));
        credentials.setPassword(request.getParameter(PARAMETER_NAME_PASSWORD));

        try {
            UserService service = UserService.getInstance();
            user = service.signIn(credentials);
            HttpSession session = request.getSession();
            if (user != null){
                if (!user.getFired()){
                    session.setAttribute(SESSION_ATTRIBUTE_NAME_ID, user.getId());
                    session.setAttribute(SESSION_ATTRIBUTE_ROLE, user.getRole().getName());
                    page = PagePath.HOME;
                }
            }
        } catch (ServiceException ex){
            throw new CommandException(ex);
        }
        return page;
    }
}
