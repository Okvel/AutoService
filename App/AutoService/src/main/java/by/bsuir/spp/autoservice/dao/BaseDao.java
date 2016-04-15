package by.bsuir.spp.autoservice.dao;

import by.bsuir.spp.autoservice.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;

public interface BaseDao <K, T extends Entity> {
    T findById(K id) throws DaoException;
    Collection<T> findAll() throws DaoException;
    K save(T entity) throws DaoException;
}
