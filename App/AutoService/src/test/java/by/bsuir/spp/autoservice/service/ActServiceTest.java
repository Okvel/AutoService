package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.*;

public class ActServiceTest extends ServiceTest{
    private static ActService service = ActService.getInstance();

    @Test
    public void findAllPassingActsTest(){
        try {
            ArrayList<Act> acts = new ArrayList<>(service.findAllPassingActs());
            assertFalse(acts.isEmpty());
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void findAllAcceptanceActsTest(){
        try {
            ArrayList<Act> acts = new ArrayList<>(service.findAllAcceptanceActs());
            assertFalse(acts.isEmpty());
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void findByIdTest(){
        final Long ID = 1L;
        try{
            Act act = service.findById(ID);
            assertNotNull(act);
            //TODO НАЮХ НАДО СРАВНЕНИЕ ID
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void saveAcceptanceActTest(){
        Act act = new Act();
        act.setDescription("Test act");
        act.setDate(new Date());
        act.setType(ActType.ACCEPTANCE);
        Car car = new Car();
        CarModel carModel = new CarModel();
        carModel.setName("model");
        carModel.setVendor("vendor");
        car.setModel(carModel);
        car.setVin("VIIIIIIIIIN");
        car.setRegistrationNumber("8888AI-2");
        act.setCar(car);
        Client client = new Client();
        client.setPassportId("PASSPORT");
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
        client.setPersonInformation(person);
        act.setClient(client);
        User user = new User();
        user.setId(4L);
        act.setManager(user);
        try{
            assertTrue(service.save(act));
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void savePassingActTest() {
        Act act = new Act();
        act.setDescription("Test act");
        act.setDate(new Date());
        act.setType(ActType.PASSING);
        Car car = new Car();
        CarModel carModel = new CarModel();
        carModel.setName("model");
        carModel.setVendor("vendor");
        car.setModel(carModel);
        car.setVin("VIIIIIIIIIN");
        car.setRegistrationNumber("8888AI-2");
        act.setCar(car);
        Client client = new Client();
        client.setPassportId("PASSPORT");
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
        client.setPersonInformation(person);
        act.setClient(client);
        User user = new User();
        user.setId(4L);
        act.setManager(user);
        try{
            assertTrue(service.save(act));
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }
}
