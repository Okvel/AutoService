package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import static junit.framework.TestCase.*;

public class DetailApplicationServiceTest extends ServiceTest {
    private static DetailApplicationService service = DetailApplicationService.getInstance();
    @Test
    public void findAllFreeApplicationsTest(){
        try{
            ArrayList<DetailApplication> applications = new ArrayList<>(service.findAllFreeApplications());
            assertFalse(applications.isEmpty());
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void saveTest(){
        DetailApplication application = new DetailApplication();
        application.setCount(1);
        Detail detail = new Detail();
        detail.setName("detail");
        application.setDetail(detail);
        User user = new User();
        UserRole role = new UserRole();
        role.setName("MECHANIC");
        user.setRole(role);
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
        user.setPersonInfo(person);
        user.setFired(false);
        application.setMechanic(user);
        Credentials credentials = new Credentials();
        credentials.setLogin("LOGIN");
        credentials.setPassword("PASSWORD");
        user.setCredentials(credentials);
        try{
            assertTrue(service.save(application));
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

}
