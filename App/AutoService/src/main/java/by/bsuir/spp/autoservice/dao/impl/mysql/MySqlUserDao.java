package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.UserDao;
import by.bsuir.spp.autoservice.dao.factory.impl.MySqlDaoFactory;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Person;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.entity.UserRole;

import javax.management.relation.Role;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlUserDao implements UserDao {
    private static final String SQL_SELECT_ALL = "SELECT `user`.`id`, `role_id`, `person_id`, `first_name`, `last_name`," +
            "`patronymic`, `country`, `city`, `street`, `building`, `room`, `phone_number`, `role`.`name`, `experience` " +
            "FROM user JOIN role ON user.role_id = role.id JOIN person ON user.person_id = person.id";

    private static MySqlUserDao instance = new MySqlUserDao();

    private MySqlUserDao() {}

    public static MySqlUserDao getInstance() {
        return instance;
    }

    @Override
    public User findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public ArrayList<User> findAll() throws DaoException {
        ArrayList<User> users = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)
                ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(fillUser(resultSet));
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return users;
    }

    private User fillUser(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong("person_id"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setPatronymic(resultSet.getString("patronymic"));
        person.setCountry(resultSet.getString("country"));
        person.setCity(resultSet.getString("city"));
        person.setStreet(resultSet.getString("street"));
        person.setBuilding(resultSet.getString("building"));
        person.setRoom(resultSet.getString("room"));
        person.setPhoneNumber(resultSet.getString("phone_number"));
        UserRole role = new UserRole();
        role.setId(resultSet.getByte("role_id"));
        role.setName(resultSet.getString("role.name"));
        User user = new User();
        user.setId(resultSet.getLong("user.id"));
        user.setRole(role);
        user.setPersonInfo(person);
        user.setExperience(resultSet.getInt("experience"));

        return user;
    }
}
