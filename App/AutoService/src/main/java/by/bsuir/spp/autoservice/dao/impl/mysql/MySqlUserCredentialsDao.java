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
import java.util.List;

public class MySqlUserCredentialsDao implements UserCredentialsDao {
    private static final String SQL_INSERT = "INSERT INTO user_cridential(login, password) VALUES (?, ?)";

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
}
