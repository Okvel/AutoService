package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.*;
import org.junit.Test;
import static junit.framework.TestCase.*;

import java.util.ArrayList;
import java.util.Date;

public class RepairReportServiceTest extends ServiceTest{
    private RepairReportService service = RepairReportService.getInstance();

    @Test
    public void findAllTest(){
        try{
            ArrayList<RepairReport> reports = new ArrayList<>(service.findAll());
            assertFalse(reports.isEmpty());
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void saveTest(){
        RepairReport report = new RepairReport();
        report.setDescription("Test report");
        report.setStartDate(new Date());
        report.setEndDate(new Date());
        User user = new User();
        user.setId(2L);
        report.setMechanic(user);
        Car car = new Car();
        car.setId(3L);
        report.setCar(car);
        try{
            assertTrue(service.save(report));
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

}
