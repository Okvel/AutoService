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
        Client client = new Client();
        client.setPersonInformation(new Person());
        act.setClient(client);
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

    public void setLastName(String lastName) {
        act.getClient().getPersonInformation().setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        act.getClient().getPersonInformation().setFirstName(firstName);
    }

    public void setPatronymic(String patronymic) {
        act.getClient().getPersonInformation().setPatronymic(patronymic);
    }

    public void setCountry(String country) {
        act.getClient().getPersonInformation().setCountry(country);
    }

    public void setCity(String city) {
        act.getClient().getPersonInformation().setCity(city);
    }

    public void setStreet(String street) {
        act.getClient().getPersonInformation().setStreet(street);
    }

    public void setBuilding(String building) {
        act.getClient().getPersonInformation().setBuilding(building);
    }

    public void setRoom(String room) {
        act.getClient().getPersonInformation().setRoom(room);
    }

    public void setPhone(String phone) {
        act.getClient().getPersonInformation().setPhoneNumber(phone);
    }

    public void setPassport(String passport) {
        act.getClient().setPassportId(passport);
    }

    public void setRegistrationNumber(String registrationNumber) {
        act.getCar().setRegistrationNumber(registrationNumber);
    }

    public void setVin(String vin){
        act.getCar().setVin(vin);
    }

    public void setModel(String model){
        act.getCar().getModel().setName(model);
    }

    public void setVendor(String vendor){
        act.getCar().getModel().setVendor(vendor);
    }

    public void setDate(String date){
        act.setDate(java.sql.Date.valueOf(date));
    }

    public void setDescription(String description) {
        act.setDescription(description);
    }

    public void setType(String actType){
        act.setType(ActType.valueOf(actType.toUpperCase()));
    }
}
