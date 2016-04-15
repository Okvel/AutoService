package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.CarDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.entity.Car;

import java.util.Collection;

public class MySqlCarDao implements CarDao {
    private static MySqlCarDao instance = new MySqlCarDao();

    private MySqlCarDao() {}

    public static MySqlCarDao getInstance() {
        return instance;
    }

    @Override
    public Car findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<Car> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Car entity) throws DaoException {
        return null;
    }
}
