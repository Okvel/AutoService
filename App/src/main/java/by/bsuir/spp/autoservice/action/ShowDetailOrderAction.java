package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.service.DetailApplicationService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

public class ShowDetailOrderAction implements Action {
    private static Logger logger = Logger.getLogger(ShowDetailOrderAction.class);

    private DetailApplication application;
    private Long id;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try{
            DetailApplicationService service = DetailApplicationService.getInstance();
            application = service.findById(id);
            result = SUCCESS;
        } catch (ServiceException ex){
            logger.error(ex);
        }

        return result;
    }

    public DetailApplication getApplication() {
        return application;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
