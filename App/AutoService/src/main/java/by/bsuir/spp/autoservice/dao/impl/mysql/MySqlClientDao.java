package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.ClientDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.entity.Client;

import java.util.Collection;

public class MySqlClientDao implements ClientDao {
    private static MySqlClientDao instance = new MySqlClientDao();

    private MySqlClientDao() {}

    public static MySqlClientDao getInstance() {
        return instance;
    }

    @Override
    public Client findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<Client> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Client entity) throws DaoException {
        return null;
    }
}
