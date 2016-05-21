package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.*;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.io.StringReader;
import java.util.Date;

public class SaveActAction implements Action {
    private static Logger logger = Logger.getLogger(SaveActAction.class);
    private Act act = new Act();
    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try{
            ActService service = ActService.getInstance();
            if (service.save(act)){
                result = SUCCESS;
            }
        } catch (ServiceException ex){
            logger.error("Save act action error", ex);
        }
        return result;
    }

    public void setManager(User manager) {
        act.setManager(manager);
    }

    public void setClient(Client client) {
        act.setClient(client);
    }

    public void setCar(Car car) {
        act.setCar(car);
    }

    public void setDate(Date date){
        act.setDate(date);
    }

    public void setDescription(String description) {
        act.setDescription(description);
    }

    public void setType(ActType actType){
        act.setType(actType);
    }
}
