package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RoleDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.UserRole;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class MySqlRoleDao implements RoleDao {
    private static final String SQL_SELECT_ALL = "SELECT id, name FROM role";

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
        ArrayList<UserRole> roles = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement()
                ) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                roles.add(fillRole(resultSet));
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return roles;
    }

    @Override
    public Byte save(UserRole entity) throws DaoException {
        return null;
    }

    private UserRole fillRole(ResultSet resultSet) throws SQLException {
        UserRole role = new UserRole();
        role.setId(resultSet.getByte(COLUMN_NAME_ID));
        role.setName(resultSet.getString(COLUMN_NAME_ROLE_NAME));

        return role;
    }
}
