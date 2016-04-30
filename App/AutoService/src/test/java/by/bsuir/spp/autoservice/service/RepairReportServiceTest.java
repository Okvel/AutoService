package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.*;
import org.junit.Test;
import static junit.framework.TestCase.*;

import java.util.ArrayList;
import java.util.Date;

public class RepairReportServiceTest extends ServiceTest{
    RepairReportService service = RepairReportService.getInstance();

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
        Person person = new Person();
        person.setLastName("Lastname");
        person.setFirstName("Firstname");
        person.setPatronymic("Patronymic");
        person.setPhoneNumber("12345789123");
        person.setRoom("13");
        person.setBuilding("78");
        person.setStreet("Street");
        person.setCity("City");
        person.setCountry("Country");
        User user = new User();
        user.setFired(false);
        UserRole role = new UserRole();
        role.setName("MECHANIC");
        user.setRole(role);
        Credentials credentials = new Credentials();
        credentials.setLogin("LOGIN");
        credentials.setPassword("PASSWORD");
        user.setCredentials(credentials);
        user.setPersonInfo(person);
        report.setMechanic(user);
        Car car = new Car();
        CarModel carModel = new CarModel();
        carModel.setName("model");
        carModel.setVendor("vendor");
        car.setModel(carModel);
        car.setVin("VIIIIIIIIIN");
        car.setRegistrationNumber("8888 AI-2");
        report.setCar(car);
        try{
            assertTrue(service.save(report));
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

}
