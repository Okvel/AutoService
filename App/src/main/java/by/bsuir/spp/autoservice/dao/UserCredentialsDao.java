package by.bsuir.spp.autoservice.dao;

import by.bsuir.spp.autoservice.entity.Credentials;

public interface UserCredentialsDao extends BaseDao<Long, Credentials> {
    int countByLogin(String login) throws DaoException;
    Long findByCredentials(Credentials entity) throws DaoException;
}
