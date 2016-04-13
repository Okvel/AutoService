package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.BaseDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.PersonDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Person;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlPersonDao implements PersonDao {
    private static final String SQL_INSERT = "INSERT INTO person(first_name, last_name, patronymic, country, city, " +
            "street, building, room, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static MySqlPersonDao instance = new MySqlPersonDao();

    private MySqlPersonDao() {}

    public static MySqlPersonDao getInstance() {
        return instance;
    }

    @Override
    public Person findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public ArrayList<Person> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Person entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT)
                ) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setObject(3, entity.getPatronymic());
            statement.setObject(4, entity.getCountry());
            statement.setObject(5, entity.getCity());
            statement.setObject(6, entity.getStreet());
            statement.setObject(7, entity.getBuilding());
            statement.setObject(8, entity.getRoom());
            statement.setString(9, entity.getPhoneNumber());
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
