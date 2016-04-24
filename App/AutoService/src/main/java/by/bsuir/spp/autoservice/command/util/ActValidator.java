package by.bsuir.spp.autoservice.command.util;

import by.bsuir.spp.autoservice.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class ActValidator {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static final String PARAMETER_NAME_DESCRIPTION = "description";
    private static final String PARAMETER_NAME_DATE = "date";
    private static final String PARAMETER_NAME_ACT_TYPE = "act_type";
    private static final String PARAMETER_NAME_FIRST_NAME = "first_name";
    private static final String PARAMETER_NAME_LAST_NAME = "last_name";
    private static final String PARAMETER_NAME_PATRONYMIC = "patronymic";
    private static final String PARAMETER_NAME_COUNTRY = "country";
    private static final String PARAMETER_NAME_CITY = "city";
    private static final String PARAMETER_NAME_STREET = "street";
    private static final String PARAMETER_NAME_BUILDING = "building";
    private static final String PARAMETER_NAME_ROOM = "room";
    private static final String PARAMETER_NAME_PHONE = "phone";
    private static final String PARAMETER_NAME_PASSPORT = "passport";
    private static final String PARAMETER_NAME_REGISTRATION_NUMBER = "registration_number";
    private static final String PARAMETER_NAME_VIN = "vin";
    private static final String PARAMETER_NAME_MODEL = "model";
    private static final String PARAMETER_NAME_VENDOR = "vendor";


    private ActValidator(){}

    public static Act validate(HttpServletRequest request){
        Act act = null;
        String date = request.getParameter(PARAMETER_NAME_DATE);
        if (!date.isEmpty()){
            act = new Act();
            User administrator = new User();
            administrator.setId(Long.parseLong(request.getParameter(SESSION_ATTRIBUTE_NAME_ID)));
            ActType type = ActType.valueOf(request.getParameter(PARAMETER_NAME_ACT_TYPE).toUpperCase());
            Client client = new Client();
            Person person = new Person();
            person.setFirstName(request.getParameter(PARAMETER_NAME_FIRST_NAME));
            person.setLastName(request.getParameter(PARAMETER_NAME_LAST_NAME));
            person.setPatronymic(request.getParameter(PARAMETER_NAME_PATRONYMIC));
            person.setCountry(request.getParameter(PARAMETER_NAME_COUNTRY));
            person.setCity(request.getParameter(PARAMETER_NAME_CITY));
            person.setStreet(request.getParameter(PARAMETER_NAME_STREET));
            person.setBuilding(request.getParameter(PARAMETER_NAME_BUILDING));
            person.setRoom(request.getParameter(PARAMETER_NAME_ROOM));
            person.setPhoneNumber(request.getParameter(PARAMETER_NAME_PHONE));
            client.setPersonInformation(person);
            client.setPassportId(request.getParameter(PARAMETER_NAME_PASSPORT));
            Car car = new Car();
            car.setRegistrationNumber(request.getParameter(PARAMETER_NAME_REGISTRATION_NUMBER));
            car.setVin(request.getParameter(PARAMETER_NAME_VIN));
            CarModel carModel = new CarModel();
            carModel.setName(request.getParameter(PARAMETER_NAME_MODEL));
            carModel.setVendor(request.getParameter(PARAMETER_NAME_VENDOR));
            car.setModel(carModel);
            act.setCar(car);
            act.setManager(administrator);
            act.setDate(Date.valueOf(date));
            act.setClient(client);
            act.setType(type);
            act.setDescription(request.getParameter(PARAMETER_NAME_DESCRIPTION));
        }
        return act;
    }
}
