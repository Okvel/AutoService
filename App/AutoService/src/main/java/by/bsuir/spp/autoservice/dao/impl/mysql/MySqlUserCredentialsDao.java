package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.UserCredentialsDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Credentials;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlUserCredentialsDao implements UserCredentialsDao {
    private static final String SQL_INSERT = "INSERT INTO user_credential(login, password) VALUES (?, ?)";
    private static final String SQL_SELECT_BY_LOGIN_AND_PASSWORD = "SELECT id FROM user_credential WHERE login = ? AND password = ?";
    private static final String SQL_COUNT_USER_BY_LOGIN = "SELECT COUNT(login) AS count FROM user_credential WHERE login = ?";

    private static final String COLUMN_NAME_ID = "id";
    private static MySqlUserCredentialsDao instance = new MySqlUserCredentialsDao();

    private MySqlUserCredentialsDao() {}

    public static MySqlUserCredentialsDao getInstance() {
        return instance;
    }

    @Override
    public Credentials findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<Credentials> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Credentials entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
                ) {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            if (statement.executeUpdate() == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                id = resultSet.getLong(1);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return id;
    }

    @Override
    public int countByLogin(String login) throws DaoException {
        int result;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_COUNT_USER_BY_LOGIN)
        ) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("count");
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return result;
    }

    @Override
    public Long findByCredentials(Credentials entity) throws DaoException {
        Long id = null;
        try(
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_LOGIN_AND_PASSWORD);
                ){
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getLong(COLUMN_NAME_ID);
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return id;
    }
}
