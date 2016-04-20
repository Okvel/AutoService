package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.DetailDao;
import by.bsuir.spp.autoservice.entity.Detail;

import java.util.Collection;

public class MySqlDetailDao implements DetailDao {
    private static MySqlDetailDao instance = new MySqlDetailDao();

    private MySqlDetailDao() {}

    public static MySqlDetailDao getInstance() {
        return instance;
    }

    @Override
    public Detail findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<Detail> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Detail entity) throws DaoException {
        return null;
    }
}
