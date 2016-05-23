package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class SignInAction implements Action {
    private static final String LOGIN_ERROR = "login_error";
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static final String SESSION_ATTRIBUTE_ROLE = "role";
    private static final String MESSAGE = "Login or password is incorrect";

    private static Logger logger = Logger.getLogger(SignInAction.class);
    private Credentials credentials = new Credentials();

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        User user;
        try {
            UserService service = UserService.getInstance();
            user = service.signIn(credentials);
            HttpSession session = ServletActionContext.getRequest() .getSession();
            if (user != null){
                if (!user.getFired()){
                    session.setAttribute(SESSION_ATTRIBUTE_NAME_ID, user.getId());
                    session.setAttribute(SESSION_ATTRIBUTE_ROLE, user.getRole().getName());
                    result = SUCCESS;
                }
            } else {
                result = LOGIN_ERROR;
            }
        } catch (ServiceException ex){
            logger.error("Login action error", ex);
        }

        return result;
    }

    public void setLogin(String login) {
        credentials.setLogin(login);
    }

    public void setPassword(String password) {
        credentials.setPassword(password);
    }

    public String getMessage() {
        return MESSAGE;
    }
}
