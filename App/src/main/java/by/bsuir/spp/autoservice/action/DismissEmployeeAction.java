package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

public class DismissEmployeeAction implements Action {
    private static Logger logger = Logger.getLogger(DismissEmployeeAction.class);
    private Long id;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try{
            UserService service = UserService.getInstance();
            if (service.deleteById(id)){
                result = SUCCESS;
            }
        } catch (ServiceException ex){
            logger.error("Dissmiss employee action error", ex);
        }
        return result;
    }

    public void setEmployee(Long id){
        this.id = id;
    }
}
