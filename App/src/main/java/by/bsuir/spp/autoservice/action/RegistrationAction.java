package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.action.util.RegistrationValidator;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserRoleService;
import by.bsuir.spp.autoservice.service.UserService;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.List;

public class RegistrationAction implements Action {
    private static final String REG_ERROR = "reg_error";
    private static final String SUCCESS_MESSAGE = "Employee was successfully registered";
    private static final String VALIDATE_ERROR_MESSAGE = "Some field contains an invalid value";
    private static final String EXIST_ERROR_MESSAGE = "Such user is already exist";

    private static Logger logger = Logger.getLogger(DismissEmployeeAction.class);

    private String text;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Byte roleId;
    private String message;
    private List<UserRole> roles;

    @Override
    public String execute() throws Exception {
        String result;
        User user = RegistrationValidator.validate(login, password, firstName, lastName, phone, roleId);
        UserRoleService userRoleService = UserRoleService.getInstance();
        if (user != null) {
            try {
                UserService service = UserService.getInstance();
                if (service.save(user)) {
                    result = SUCCESS;
                    text = SUCCESS_MESSAGE;
                } else {
                    result = REG_ERROR;
                    roles = userRoleService.findAll();
                    message = EXIST_ERROR_MESSAGE;
                }
            } catch (ServiceException ex) {
                logger.error("Registration action error", ex);
                result = ERROR;
            }
        } else {
            result = REG_ERROR;
            roles = userRoleService.findAll();
            message = VALIDATE_ERROR_MESSAGE;
        }

        return result;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    public String getMessage() {
        return message;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public String getText() {
        return text;
    }
}
