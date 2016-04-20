package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.DetailApplicationDao;
import by.bsuir.spp.autoservice.entity.DetailApplication;

import java.util.Collection;

public class MySqlDetailApplicationDao implements DetailApplicationDao {
    private static MySqlDetailApplicationDao instance = new MySqlDetailApplicationDao();

    private MySqlDetailApplicationDao() {}

    public static MySqlDetailApplicationDao getInstance() {
        return instance;
    }

    @Override
    public DetailApplication findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<DetailApplication> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(DetailApplication entity) throws DaoException {
        return null;
    }
}
