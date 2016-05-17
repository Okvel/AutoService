package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RoleDao;
import by.bsuir.spp.autoservice.entity.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserRoleService extends BaseService {
    private static UserRoleService instance = new UserRoleService();
    private static RoleDao dao = factory.getRoleDao();

    private UserRoleService() {}

    public static UserRoleService getInstance() {
        return instance;
    }

    public List<UserRole> findAll() throws ServiceException {
        ArrayList<UserRole> roles;
        try {
            roles = new ArrayList<>(dao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return roles;
    }
}
