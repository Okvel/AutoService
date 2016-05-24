package by.bsuir.spp.autoservice.action.util;

import by.bsuir.spp.autoservice.entity.Car;
import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.entity.User;

import java.sql.Date;

public class RepairReportValidator {
    private RepairReportValidator() {}

    public static RepairReport validate(long mechanicId, long carId, String startDateValue, String endDateValue,
                                        String description) {
        RepairReport report = null;
        if (!startDateValue.isEmpty() && !endDateValue.isEmpty()) {
            Date startDate = Date.valueOf(startDateValue);
            Date endDate = Date.valueOf(endDateValue);
            if (startDate.compareTo(endDate) < 0) {
                Car car = new Car();
                car.setId(carId);
                User mechanic = new User();
                mechanic.setId(mechanicId);
                report = new RepairReport();
                report.setCar(car);
                report.setMechanic(mechanic);
                report.setStartDate(startDate);
                report.setEndDate(endDate);
                report.setDescription(description);
            }
        }

        return report;
    }
}
