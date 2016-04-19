package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.ActDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.*;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySqlActDao implements ActDao {
    private static final String SQL_SELECT_ALL = "SELECT id, worker_id, client_id, car_id, date, type, description FROM act";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
    private static final String SQL_SELECT_ALL_PASSING_ACTS = SQL_SELECT_ALL + " WHERE type = 'PASSING'";
    private static final String SQL_SELECT_ALL_ACCEPTANCE_ACTS = SQL_SELECT_ALL + " WHERE type = 'ACCEPTANCE'";
    private static final String SQL_INSERT = "INSERT INTO act(worker_id, client_id, car_id, date, type, description) " +
            "VALUES(?,?,?,?,?,?)";

    private static final String COLUMN_NAME_WORKER_ID = "worker_id";
    private static final String COLUMN_NAME_CLIENT_ID = "client_id";
    private static final String COLUMN_NAME_CAR_ID = "car_id";
    private static final String COLUMN_NAME_DATE = "date";
    private static final String COLUMN_NAME_TYPE = "type";
    private static final String COLUMN_NAME_DESCRIPTION = "description";

    private static MySqlActDao instance = new MySqlActDao();

    private MySqlActDao() {}

    public static MySqlActDao getInstance() {
        return instance;
    }

    @Override
    public Act findById(Long id) throws DaoException {
        Act act = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                act = fillAct(resultSet);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return act;
    }

    @Override
    public Collection<Act> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Act entity) throws DaoException {
        Long id = null;
        try(
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
            ){
            MySqlUserDao mySqlUserDao = MySqlUserDao.getInstance();
            MySqlClientDao mySqlClientDao = MySqlClientDao.getInstance();
            MySqlCarDao mySqlCarDao = MySqlCarDao.getInstance();
            statement.setLong(1, mySqlUserDao.save(entity.getManager()));
            statement.setLong(2,mySqlClientDao.save(entity.getClient()));
            statement.setLong(3, mySqlCarDao.save(entity.getCar()));
            statement.setDate(4, (Date) entity.getDate());
            statement.setString(5, entity.getType().toString());
            statement.setString(6, entity.getDescription());
            if (statement.executeUpdate() == 1){
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                id = resultSet.getLong(1);
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return id;
    }

    @Override
    public Collection<Act> findAllAcceptanceActs() throws DaoException {
        return findAllActs(SQL_SELECT_ALL_ACCEPTANCE_ACTS);
    }

    @Override
    public Collection<Act> findAllPassingActs() throws DaoException {
        return findAllActs(SQL_SELECT_ALL_PASSING_ACTS);
    }

    private Collection<Act> findAllActs(String query) throws DaoException{
        ArrayList<Act> acts = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                acts.add(fillAct(resultSet));
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return acts;
    }

    private Act fillAct(ResultSet resultSet) throws SQLException, DaoException {
        MySqlUserDao userDao = MySqlUserDao.getInstance();
        MySqlClientDao clientDao = MySqlClientDao.getInstance();
        MySqlCarDao carDao = MySqlCarDao.getInstance();
        User manager = userDao.findById(resultSet.getLong(COLUMN_NAME_WORKER_ID));
        Client client = clientDao.findById(resultSet.getLong(COLUMN_NAME_CLIENT_ID));
        Car car = carDao.findById(resultSet.getLong(COLUMN_NAME_CAR_ID));
        Act act = new Act();
        act.setId(resultSet.getLong(COLUMN_NAME_ID));
        act.setManager(manager);
        act.setClient(client);
        act.setCar(car);
        act.setDate(resultSet.getDate(COLUMN_NAME_DATE));
        act.setType(ActType.valueOf(resultSet.getString(COLUMN_NAME_TYPE)));
        Object description = resultSet.getObject(COLUMN_NAME_DESCRIPTION);
        if (description != null) {
            act.setDescription((String) description);
        }

        return act;
    }
}
