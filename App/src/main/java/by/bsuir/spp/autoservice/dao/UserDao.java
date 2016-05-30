package by.bsuir.spp.autoservice.dao;

import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.User;

import java.util.Collection;

public interface UserDao extends BaseDao<Long, User> {
    boolean deleteById(Long id) throws DaoException;
    User findByCredentials(Credentials credentials) throws DaoException;
    Collection<User> findAllNotDismissed() throws DaoException;
}
