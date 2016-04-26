package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class UserServiceTest extends ServiceTest {
    private static UserService service = UserService.getInstance();

    @Test
    public void selectUserTest() {
        final Long ID = 2L;

        try {
            User user = service.findById(ID);
            assertNotNull(user);
            assertEquals(ID, user.getId());
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void selectNotExistingUserTest() {
        final Long ID = 0L;

        try {
            User user = service.findById(ID);
            assertNull(user);
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void saveNewUserTest() {
        Credentials credentials = new Credentials();
        credentials.setLogin("newUser");
        credentials.setPassword("newUser");
        UserRole role = new UserRole();
        role.setId((byte) 1);
        Person person = new Person();
        person.setFirstName("User");
        person.setLastName("New");
        person.setPhoneNumber("375295553311");
        User newUser = new User();
        newUser.setCredentials(credentials);
        newUser.setRole(role);
        newUser.setPersonInfo(person);
        try {
            assertTrue(service.save(newUser));
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void dismissUserTest() {
        final Long ID = 2L;

        try {
            assertTrue(service.deleteById(ID));
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void signInTest() {
        final Long USER_ID = 3L;

        Credentials credentials = new Credentials();
        credentials.setLogin("admin");
        credentials.setPassword("admin");
        try {
            User user = service.signIn(credentials);
            assertNotNull(user);
            assertEquals(USER_ID, user.getId());
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }

    @Test
    public void wrongPasswordSignInTest() {
        Credentials credentials = new Credentials();
        credentials.setLogin("admin");
        credentials.setPassword("password");
        try {
            User user = service.signIn(credentials);
            assertNull(user);
        } catch (ServiceException ex) {
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }
}
