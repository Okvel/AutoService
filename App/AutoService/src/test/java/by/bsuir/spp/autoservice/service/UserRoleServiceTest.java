package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.UserRole;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

public class UserRoleServiceTest extends ServiceTest {
    private static UserRoleService service = UserRoleService.getInstance();

    @Test
    public void findAllTest(){
        try{
            ArrayList<UserRole> roles = new ArrayList<>(service.findAll());
            assertFalse(roles.isEmpty());
        } catch (ServiceException ex){
            fail("Service exception occurred\n\t" + ex.getMessage());
        }
    }
}
