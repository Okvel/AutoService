package by.bsuir.spp.autoservice.action.util;

import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegistrationValidator {
    private static final String LOGIN_REGEX = "^([\\w\\-\\.]+)@(([\\w\\-]+\\.)+)([a-zA-Z]{2,4})$";
    private static final String PASSWORD_REGEX = "^.{5,64}$";
    private static final String NAME_REGEX = "^[A-Za-zА-Яа-я]+[A-Za-zА-Яа-я-\\s\\.]*[A-Za-zА-Яа-я]$";
    private static final String PHONE_REGEX = "^\\d[\\d-]{7,}\\d$";

    private RegistrationValidator() {}

    public static User validate(String login, String password, String firstName, String lastName, String phone, Byte roleId) {
        User user = null;
        if (Pattern.matches(LOGIN_REGEX, login) && Pattern.matches(PASSWORD_REGEX, password) &&
                Pattern.matches(NAME_REGEX, firstName) && Pattern.matches(NAME_REGEX, lastName) &&
                Pattern.matches(PHONE_REGEX, phone)) {
            Credentials credentials = new Credentials();
            credentials.setLogin(login);
            credentials.setPassword(password);
            Person person = new Person();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setPhoneNumber(phone);
            UserRole role = new UserRole();
            role.setId(roleId);
            user = new User();
            user.setCredentials(credentials);
            user.setPersonInfo(person);
            user.setRole(role);
        }

        return user;
    }
}
