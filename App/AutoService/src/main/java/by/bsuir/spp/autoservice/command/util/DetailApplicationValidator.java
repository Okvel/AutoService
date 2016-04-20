package by.bsuir.spp.autoservice.command.util;

import by.bsuir.spp.autoservice.entity.Detail;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class DetailApplicationValidator {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static final String PARAMETER_NAME_DETAIL_NAME = "detail_name";
    private static final String PARAMETER_NAME_COUNT = "count";

    private static final String COUNT_REGEX = "\\d+";

    private DetailApplicationValidator() {}

    public static DetailApplication validate(HttpServletRequest request) {
        DetailApplication application = null;
        String countValue = request.getParameter(PARAMETER_NAME_COUNT);
        if (Pattern.matches(COUNT_REGEX, countValue)) {
            application = new DetailApplication();
            User mechanic = new User();
            mechanic.setId((Long) request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_ID));
            Detail detail = new Detail();
            detail.setName(request.getParameter(PARAMETER_NAME_DETAIL_NAME));
            application.setMechanic(mechanic);
            application.setDetail(detail);
            application.setCount(Integer.parseInt(countValue));
        }

        return application;
    }
}
