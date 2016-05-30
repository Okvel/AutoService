package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ShowDismissPageAction implements Action {
    private static Logger logger = Logger.getLogger(SignInAction.class);

    private ArrayList<User> users;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        UserService service = UserService.getInstance();
        try{
            users = service.findAllNotDismissed();
            result = SUCCESS;
        } catch (ServiceException ex){
            logger.error("Show dismiss page action error", ex);
        }

        return result;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
