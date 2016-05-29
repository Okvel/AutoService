package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.Detail;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.DetailApplicationService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class SaveOrderDetailApplicationAction implements Action {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static Logger logger = Logger.getLogger(SaveOrderDetailApplicationAction.class);

    private DetailApplication application = new DetailApplication();
    {
        Detail detail = new Detail();
        application.setDetail(detail);
    }

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        User mechanic = new User();
        HttpSession session = ServletActionContext.getRequest().getSession();
        mechanic.setId((Long) session.getAttribute(SESSION_ATTRIBUTE_NAME_ID));
        application.setMechanic(mechanic);
        try{
            DetailApplicationService service = DetailApplicationService.getInstance();
            if (service.save(application)){
                result = SUCCESS;
            }
        } catch (ServiceException ex){
            logger.error("Save order detail application action error", ex);
        }
        return result;
    }


    public void setDetail(Long id) {
        application.getDetail().setId(id);
    }

    public void setCount(Integer count) {
        application.setCount(count);
    }
}
