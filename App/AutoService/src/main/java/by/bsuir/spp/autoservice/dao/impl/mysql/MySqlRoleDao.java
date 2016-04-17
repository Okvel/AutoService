package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RoleDao;
import by.bsuir.spp.autoservice.entity.UserRole;

import java.util.Collection;

public class MySqlRoleDao implements RoleDao {
    private static final String COLUMN_NAME_ROLE_NAME = "name";

    private static MySqlRoleDao instance = new MySqlRoleDao();

    private MySqlRoleDao() {}

    public static MySqlRoleDao getInstance() {
        return instance;
    }

    @Override
    public UserRole findById(Byte id) throws DaoException {
        return null;
    }

    @Override
    public Collection<UserRole> findAll() throws DaoException {
        return null;
    }

    @Override
    public Byte save(UserRole entity) throws DaoException {
        return null;
    }
}
