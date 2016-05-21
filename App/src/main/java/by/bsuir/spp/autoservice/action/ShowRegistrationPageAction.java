package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.UserRole;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserRoleService;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.List;

public class ShowRegistrationPageAction implements Action {
    private static Logger logger = Logger.getLogger(SignInAction.class);

    private List<UserRole> roles;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try {
            UserRoleService service = UserRoleService.getInstance();
            roles = service.findAll();
//            ServletActionContext.getRequest().setAttribute(REQUEST_ATTRIBUTE_NAME_ROLE_LIST, roles);
            result = SUCCESS;
        } catch (ServiceException ex) {
            logger.error("Show registration page action error", ex);
        }

        return result;
    }

    public List<UserRole> getRoles() {
        return roles;
    }
}
