package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.*;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


public class SaveActAction implements Action {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static Logger logger = Logger.getLogger(SaveActAction.class);
    private Act act = new Act();
    {
        Car car = new Car();
        car.setModel(new CarModel());
        act.setCar(car);
    }
    @Override
    public String execute() throws Exception {
        String result = ERROR;
        User manager = new User();
        manager.setId((Long)ServletActionContext.getRequest().getSession().getAttribute(SESSION_ATTRIBUTE_NAME_ID));
        act.setManager(manager);
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

    public void setClient(Long id) {
        Client client = new Client();
        client.setId(id);
        act.setClient(client);
    }

    public void setCarRegistrationNumber(String registrationNumber) {
        act.getCar().setRegistrationNumber(registrationNumber);
    }

    public void setCarVin(String vin){
        act.getCar().setVin(vin);
    }

    public void setCarModel(String model){
        act.getCar().getModel().setName(model);
    }

    public void setCarVendor(String vendor){
        act.getCar().getModel().setVendor(vendor);
    }

    public void setDate(String date){
        act.setDate(java.sql.Date.valueOf(date));
    }

    public void setDescription(String description) {
        act.setDescription(description);
    }

    public void setType(String actType){
        act.setType(ActType.valueOf(actType));
    }
}
