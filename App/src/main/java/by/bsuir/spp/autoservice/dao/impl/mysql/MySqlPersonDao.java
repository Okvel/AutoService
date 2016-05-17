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

class MySqlPersonDao implements PersonDao {
    private static final String SQL_SELECT_ALL = "SELECT id, first_name, last_name, patronymic, country, city, street, " +
            "building, room, phone_number FROM person";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
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
        Person person = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                person = fillPerson(resultSet);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return person;
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
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
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

    private Person fillPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong(COLUMN_NAME_ID));
        person.setFirstName(resultSet.getString(COLUMN_NAME_FIRST_NAME));
        person.setLastName(resultSet.getString(COLUMN_NAME_LAST_NAME));
        person.setPatronymic(takeNullParameter(resultSet, COLUMN_NAME_PATRONYMIC));
        person.setCountry(takeNullParameter(resultSet, COLUMN_NAME_COUNTRY));
        person.setCity(takeNullParameter(resultSet, COLUMN_NAME_CITY));
        person.setStreet(takeNullParameter(resultSet, COLUMN_NAME_STREET));
        person.setBuilding(takeNullParameter(resultSet, COLUMN_NAME_BUILDING));
        person.setRoom(takeNullParameter(resultSet, COLUMN_NAME_ROOM));
        person.setPhoneNumber(resultSet.getString(COLUMN_NAME_PHONE_NUMBER));

        return person;
    }

    private String takeNullParameter(ResultSet resultSet, String columnName) throws SQLException {
        String result = null;
        Object object = resultSet.getObject(columnName);
        if (object != null) {
            result = (String) object;
        }

        return result;
    }
}
