package by.bsuir.spp.autoservice.dao;

import by.bsuir.spp.autoservice.entity.DetailApplication;

import java.util.Collection;

public interface DetailApplicationDao extends BaseDao<Long, DetailApplication> {
    Collection<DetailApplication> findAllProcessing() throws DaoException;
}
