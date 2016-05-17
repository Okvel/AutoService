package by.bsuir.spp.autoservice.dao;

import java.util.Collection;

public interface VendorDao {
    String findById(Long id) throws DaoException;
    Collection<String> findAll() throws DaoException;
    Long save(String entity) throws DaoException;
}
