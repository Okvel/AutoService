package by.bsuir.spp.autoservice.dao;

import by.bsuir.spp.autoservice.entity.Act;

import java.util.Collection;

public interface ActDao extends BaseDao<Long, Act> {
    Collection<Act> findAllAcceptanceActs() throws DaoException;
    Collection<Act> findAllPassingActs() throws DaoException;
}
