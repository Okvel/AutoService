package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.ClientDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.PersonDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Client;
import by.bsuir.spp.autoservice.entity.Person;
import sun.util.resources.cldr.es.CalendarData_es_GT;

import javax.naming.NamingException;
import java.sql.*;
import java.util.Collection;

public class MySqlClientDao implements ClientDao {
    private static final String SQL_SELECT_ALL = "SELECT id, person_id, passport_id FROM client";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
    private static final String SQL_ISNERT = "INSERT INTO client(person_id, passport_id) VALUES(?,?)";

    private static final String COLUMN_NAME_PERSON_ID = "person_id";
    private static final String COLUMN_NAME_PASSPORT_ID = "passport_id";

    private static MySqlClientDao instance = new MySqlClientDao();

    private MySqlClientDao() {}

    public static MySqlClientDao getInstance() {
        return instance;
    }

    @Override
    public Client findById(Long id) throws DaoException {
        Client client = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = fillClient(resultSet);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return client;
    }

    @Override
    public Collection<Client> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Client entity) throws DaoException {
        Long id = null;
        try(
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_ISNERT,
                        PreparedStatement.RETURN_GENERATED_KEYS)
                ){
            MySqlPersonDao personDao = MySqlPersonDao.getInstance();
            statement.setLong(1, personDao.save(entity.getPersonInformation()));
            statement.setString(2, entity.getPassportId());
            if (statement.executeUpdate() == 1){
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                id = resultSet.getLong(1);
            }
        }catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return id;
    }

    private Client fillClient(ResultSet resultSet) throws SQLException, DaoException {
        MySqlPersonDao personDao = MySqlPersonDao.getInstance();
        Person person = personDao.findById(resultSet.getLong(COLUMN_NAME_PERSON_ID));
        Client client = new Client();
        client.setId(resultSet.getLong(COLUMN_NAME_ID));
        client.setPersonInformation(person);
        client.setPassportId(resultSet.getString(COLUMN_NAME_PASSPORT_ID));

        return client;
    }
}
