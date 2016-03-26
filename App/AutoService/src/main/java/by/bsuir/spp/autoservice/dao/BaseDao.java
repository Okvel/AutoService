package by.bsuir.spp.autoservice.dao;

import by.bsuir.spp.autoservice.entity.Entity;

import java.util.ArrayList;

public interface BaseDao <K, T extends Entity> {
    T findById(K id) throws DaoException;
    ArrayList<T> findAll() throws DaoException;
}
