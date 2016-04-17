package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.PersonDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Person;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MySqlPersonDao implements PersonDao {
    private static final String SQL_INSERT = "INSERT INTO person(first_name, last_name, patronymic, country, city, " +
            "street, building, room, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String COLUMN_NAME_FIRST_NAME = "first_name";
    private static final String COLUMN_NAME_LAST_NAME = "last_name";
    private static final String COLUMN_NAME_PATRONYMIC = "patronymic";
    private static final String COLUMN_NAME_COUNTRY = "country";
    private static final String COLUMN_NAME_CITY = "city";
    private static final String COLUMN_NAME_STREET = "street";
    private static final String COLUMN_NAME_BUILDING = "building";
    private static final String COLUMN_NAME_ROOM = "room";
    private static final String COLUMN_NAME_PHONE_NUMBER = "phone_number";

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
    public Collection<Person> findAll() throws DaoException {
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
