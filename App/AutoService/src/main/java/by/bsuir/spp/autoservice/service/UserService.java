package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.UserDao;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.util.MD5HashCreator;

import java.util.ArrayList;

public class UserService extends BaseService {
    private static UserService instance = new UserService();
    private static UserDao dao = factory.getUserDao();

    private UserService() {}

    public static UserService getInstance() {
        return instance;
    }

    public ArrayList<User> findAll() throws ServiceException {
        ArrayList<User> users;
        try {
            users = new ArrayList<>(dao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return users;
    }

    public boolean save(User user) throws ServiceException {
        boolean result = false;
        user.getCredentials().setPassword(MD5HashCreator.create(user.getCredentials().getPassword()));
        try {
            if (dao.save(user) != null) {
                result = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return result;
    }

    public boolean deleteById(Long id) throws ServiceException {
        boolean result;
        try {
            result = dao.deleteById(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return result;
    }
}
