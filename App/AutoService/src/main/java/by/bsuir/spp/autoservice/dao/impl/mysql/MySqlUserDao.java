package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.UserCredentialsDao;
import by.bsuir.spp.autoservice.dao.UserDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Credentials;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySqlUserDao implements UserDao {
    private static final String SQL_SELECT_ALL = "SELECT id, role_id, person_id, fired FROM user";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
    private static final String SQL_SELECT_BY_CREDENTIALS = SQL_SELECT_ALL + " WHERE cridential_id = ?";

    private static final String SQL_INSERT = "INSERT INTO user(role_id, cridential_id, person_id) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "UPDATE user SET fired = 1 WHERE id = ?";

    private static final String COLUMN_NAME_ROLE_ID = "role_id";
    private static final String COLUMN_NAME_PERSON_ID = "person_id";
    private static final String COLUMN_NAME_FIRED = "fired";

    private static MySqlUserDao instance = new MySqlUserDao();

    private MySqlUserDao() {}

    public static MySqlUserDao getInstance() {
        return instance;
    }

    @Override
    public User findById(Long id) throws DaoException {
        User user = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = fillUser(resultSet);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return user;
    }

    @Override
    public Collection<User> findAll() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement()
                ) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                users.add(fillUser(resultSet));
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return users;
    }

    @Override
    public User findByCredentials(Credentials credentials) throws DaoException {
        User user = null;
        try(
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_CREDENTIALS)
                ){
            MySqlUserCredentialsDao credentialsDao = MySqlUserCredentialsDao.getInstance();
            statement.setObject(1, credentialsDao.findByCredentials(credentials));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = fillUser(resultSet);
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return user;
    }

    @Override
    public Long save(User entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
                ) {
            MySqlUserCredentialsDao credentialsDao = MySqlUserCredentialsDao.getInstance();
            if (credentialsDao.countByLogin(entity.getCredentials().getLogin()) == 0) {
                MySqlPersonDao personDao = MySqlPersonDao.getInstance();
                statement.setByte(1, entity.getRole().getId());
                statement.setLong(2, credentialsDao.save(entity.getCredentials()));
                statement.setLong(3, personDao.save(entity.getPersonInfo()));
                if (statement.executeUpdate() == 1) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    resultSet.next();
                    id = resultSet.getLong(1);
                }
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return id;
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        boolean result = false;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE)
                ) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 1) {
                result = true;
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    private User fillUser(ResultSet resultSet) throws SQLException, DaoException {
        MySqlPersonDao personDao = MySqlPersonDao.getInstance();
        MySqlRoleDao roleDao = MySqlRoleDao.getInstance();
        Person person = personDao.findById(resultSet.getLong(COLUMN_NAME_PERSON_ID));
        UserRole role = roleDao.findById(resultSet.getByte(COLUMN_NAME_ROLE_ID));
        User user = new User();
        user.setId(resultSet.getLong(COLUMN_NAME_ID));
        user.setRole(role);
        user.setPersonInfo(person);
        user.setFired(resultSet.getBoolean(COLUMN_NAME_FIRED));
        return user;
    }
}
