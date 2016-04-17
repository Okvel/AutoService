package by.bsuir.spp.autoservice.dao;

import by.bsuir.spp.autoservice.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;

interface BaseDao <K, T extends Entity> {
    final String COLUMN_NAME_ID = "id";

    T findById(K id) throws DaoException;
    Collection<T> findAll() throws DaoException;
    K save(T entity) throws DaoException;
}
