package by.bsuir.spp.autoservice.command.util;

import by.bsuir.spp.autoservice.entity.Car;
import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class RepairReportValidator {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static final String PARAMETER_NAME_CAR_ID = "car_id";
    private static final String PARAMETER_NAME_START_DATE = "start_date";
    private static final String PARAMETER_NAME_END_DATE = "end_date";
    private static final String PARAMETER_NAME_DESCRIPTION = "description";

    private RepairReportValidator() {}

    public static RepairReport validate(HttpServletRequest request) {
        RepairReport report = null;
        String startDateValue = request.getParameter(PARAMETER_NAME_START_DATE);
        String endDateValue = request.getParameter(PARAMETER_NAME_END_DATE);
        if (!startDateValue.isEmpty() && !endDateValue.isEmpty()) {
            Date startDate = Date.valueOf(startDateValue);
            Date endDate = Date.valueOf(endDateValue);
            if (startDate.compareTo(endDate) < 0) {
                Car car = new Car();
                car.setId(Long.parseLong(request.getParameter(PARAMETER_NAME_CAR_ID)));
                User mechanic = new User();
                mechanic.setId((Long) request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_ID));
                report = new RepairReport();
                report.setCar(car);
                report.setMechanic(mechanic);
                report.setStartDate(startDate);
                report.setEndDate(endDate);
                report.setDescription(request.getParameter(PARAMETER_NAME_DESCRIPTION));
            }
        }

        return report;
    }
}
