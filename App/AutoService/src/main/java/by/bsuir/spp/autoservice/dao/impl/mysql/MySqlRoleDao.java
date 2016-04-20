package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RoleDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.UserRole;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySqlRoleDao implements RoleDao {
    private static final String SQL_SELECT_ALL = "SELECT id, name FROM role";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";

    private static final String COLUMN_NAME_ROLE_NAME = "name";

    private static MySqlRoleDao instance = new MySqlRoleDao();

    private MySqlRoleDao() {}

    public static MySqlRoleDao getInstance() {
        return instance;
    }

    @Override
    public UserRole findById(Byte id) throws DaoException {
        UserRole role = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ) {
            statement.setByte(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role = fillRole(resultSet);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return role;
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
    public Byte save(UserRole entity) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    private UserRole fillRole(ResultSet resultSet) throws SQLException {
        UserRole role = new UserRole();
        role.setId(resultSet.getByte(COLUMN_NAME_ID));
        role.setName(resultSet.getString(COLUMN_NAME_ROLE_NAME));

        return role;
    }
}
