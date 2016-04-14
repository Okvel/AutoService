package by.bsuir.spp.autoservice.command.util;

import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class RegistrationValidator {
    private static final String PARAMETER_NAME_LOGIN = "login";
    private static final String PARAMETER_NAME_PASSWORD = "password";
    private static final String PARAMETER_NAME_FIRST_NAME = "first_name";
    private static final String PARAMETER_NAME_LAST_NAME = "last_name";
    private static final String PARAMETER_NAME_PHONE = "phone";
    private static final String PARAMETER_NAME_ROLE_ID = "role_id";

    private static final String LOGIN_REGEX = "^([\\w\\-\\.]+)@(([\\w\\-]+\\.)+)([a-zA-Z]{2,4})$";
    private static final String PASSWORD_REGEX = "^.{5,64}$";
    private static final String NAME_REGEX = "^[A-Za-zА-Яа-я]+[A-Za-zА-Яа-я-\\s\\.]*[A-Za-zА-Яа-я]$";
    private static final String PHONE_REGEX = "^\\d[\\d-]{7,}\\d$";

    private RegistrationValidator() {}

    public static User validate(HttpServletRequest request) {
        User user = null;
        String login = request.getParameter(PARAMETER_NAME_LOGIN);
        String password = request.getParameter(PARAMETER_NAME_PASSWORD);
        String firstName = request.getParameter(PARAMETER_NAME_FIRST_NAME);
        String lastName = request.getParameter(PARAMETER_NAME_LAST_NAME);
        String phone = request.getParameter(PARAMETER_NAME_PHONE);
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
            role.setId(Byte.parseByte(request.getParameter(PARAMETER_NAME_ROLE_ID)));
            user = new User();
            user.setCredentials(credentials);
            user.setPersonInfo(person);
            user.setRole(role);
        }

        return user;
    }
}
