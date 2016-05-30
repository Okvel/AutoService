package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

public class DismissEmployeeAction implements Action {
    private static final String SUCCESS_MESSAGE = "Employee was successfully dismissed";
    private static final String FAIL_MESSAGE = "Employee wasn't dismissed";

    private static Logger logger = Logger.getLogger(DismissEmployeeAction.class);

    private Long id;
    private String text;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        text = FAIL_MESSAGE;
        try{
            UserService service = UserService.getInstance();
            if (service.deleteById(id)){
                result = SUCCESS;
                text = SUCCESS_MESSAGE;
            }
        } catch (ServiceException ex){
            logger.error("Dissmiss employee action error", ex);
        }
        return result;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getText() {
        return text;
    }
}
