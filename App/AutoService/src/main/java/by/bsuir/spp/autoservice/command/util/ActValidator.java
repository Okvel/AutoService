package by.bsuir.spp.autoservice.command.util;

import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.regex.Pattern;

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
    private static final String REGEX_PHONE = "\\d+";
    private static final String REGEX_WORD = "[\\wА-Яа-я\\s\\.]+";
    private static final String REGEX_BUILDING = "\\d+(\\w)?(\\\\\\d+)?";
    private static final String REGEX_ROOM = "\\d+(\\w)?";
    private static final String REGEX_PASSPORT = "\\w{2}\\d{7}";
    private static final String REGEX_REGISTRATION_NUMBEER = "\\d{4}\\s\\w{2}\\-\\d";
    private static final String REGEX_VENDOR = "[(\\d)(\\w)]+";


    private ActValidator(){}

    public static Act validate(HttpServletRequest request){
        Act act = null;
        String date = request.getParameter(PARAMETER_NAME_DATE);
        String phone = request.getParameter(PARAMETER_NAME_PHONE);
        String lastName = request.getParameter(PARAMETER_NAME_FIRST_NAME);
        String firstName = request.getParameter(PARAMETER_NAME_LAST_NAME);
        String patronymic = request.getParameter(PARAMETER_NAME_PATRONYMIC);
        String country = request.getParameter(PARAMETER_NAME_COUNTRY);
        String city = request.getParameter(PARAMETER_NAME_CITY);
        String street = request.getParameter(PARAMETER_NAME_STREET);
        String building = request.getParameter(PARAMETER_NAME_BUILDING);
        String room = request.getParameter(PARAMETER_NAME_ROOM);
        String passport = request.getParameter(PARAMETER_NAME_PASSPORT);
        String registrationNumber = request.getParameter(PARAMETER_NAME_REGISTRATION_NUMBER);
        String model = request.getParameter(PARAMETER_NAME_MODEL);
        String vendor = request.getParameter(PARAMETER_NAME_VENDOR);
        if (Pattern.matches(REGEX_PHONE, phone) && Pattern.matches(REGEX_WORD, lastName) &&
                Pattern.matches(REGEX_WORD, firstName)&& Pattern.matches(REGEX_WORD, patronymic) &&
                Pattern.matches(REGEX_WORD, country) && Pattern.matches(REGEX_WORD, city) &&
                Pattern.matches(REGEX_WORD, street) && Pattern.matches(REGEX_BUILDING, building) &&
                Pattern.matches(REGEX_ROOM, room) && Pattern.matches(REGEX_PASSPORT, passport) && !date.isEmpty() &&
                Pattern.matches(REGEX_REGISTRATION_NUMBEER, registrationNumber) && Pattern.matches(REGEX_WORD, model) &&
                Pattern.matches(REGEX_VENDOR, vendor)){
            act = new Act();
            User administrator = new User();
            administrator.setId((Long) request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_ID));
            ActType type = ActType.valueOf(request.getParameter(PARAMETER_NAME_ACT_TYPE).toUpperCase());
            Client client = new Client();
            Person person = new Person();
            person.setPhoneNumber(phone);
            person.setFirstName(lastName);
            person.setLastName(firstName);
            person.setPatronymic(patronymic);
            person.setCountry(country);
            person.setCity(city);
            person.setStreet(street);
            person.setBuilding(building);
            person.setRoom(room);
            client.setPersonInformation(person);
            client.setPassportId(passport);
            Car car = new Car();
            car.setRegistrationNumber(registrationNumber.replaceAll(" ","").replaceAll("-", ""));
            car.setVin(request.getParameter(PARAMETER_NAME_VIN));
            CarModel carModel = new CarModel();
            carModel.setName(model);
            carModel.setVendor(vendor);
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
