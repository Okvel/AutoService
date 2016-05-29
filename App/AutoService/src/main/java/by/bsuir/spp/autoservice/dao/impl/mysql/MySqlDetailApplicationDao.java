package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.DetailApplicationDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Detail;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.entity.User;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySqlDetailApplicationDao implements DetailApplicationDao {
    private static final String SQL_SELECT_ALL = "SELECT details_application.id, mechanic_id, detail_id, `count` " +
            "FROM details_application";
    private static final String SQL_SELECT_ALL_PROCESSING = SQL_SELECT_ALL + " JOIN invoice ON details_application_id = " +
            "details_application.id";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE details_application.id = ?";
    private static final String SQL_INSERT = "INSERT INTO details_application(mechanic_id, detail_id, count) VALUES (?,?,?)";

    private static final String COLUMN_NAME_APPLICATION_ID = "details_application.id";
    private static final String COLUMN_NAME_MECHANIC_ID = "mechanic_id";
    private static final String COLUMN_NAME_DETAIL_ID = "detail_id";
    private static final String COLUMN_NAME_COUNT = "count";

    private static MySqlDetailApplicationDao instance = new MySqlDetailApplicationDao();

    private MySqlDetailApplicationDao() {}

    public static MySqlDetailApplicationDao getInstance() {
        return instance;
    }

    @Override
    public DetailApplication findById(Long id) throws DaoException {
        DetailApplication application = null;
        try(
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                application = fillApplication(resultSet);
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return application;
    }

    @Override
    public Collection<DetailApplication> findAll() throws DaoException {
        return selectAll(SQL_SELECT_ALL);
    }

    @Override
    public Long save(DetailApplication entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
                ) {
            MySqlDetailDao detailDao = MySqlDetailDao.getInstance();
            statement.setLong(1, entity.getMechanic().getId());
            statement.setLong(2, detailDao.save(entity.getDetail()));
            statement.setInt(3, entity.getCount());
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
    public Collection<DetailApplication> findAllProcessing() throws DaoException {
        return selectAll(SQL_SELECT_ALL_PROCESSING);
    }

    private DetailApplication fillApplication(ResultSet resultSet) throws SQLException, DaoException {
        MySqlUserDao userDao = MySqlUserDao.getInstance();
        MySqlDetailDao detailDao = MySqlDetailDao.getInstance();
        User mechanic = userDao.findById(resultSet.getLong(COLUMN_NAME_MECHANIC_ID));
        Detail detail = detailDao.findById(resultSet.getLong(COLUMN_NAME_DETAIL_ID));
        DetailApplication application = new DetailApplication();
        application.setId(resultSet.getLong(COLUMN_NAME_APPLICATION_ID));
        application.setMechanic(mechanic);
        application.setDetail(detail);
        application.setCount(resultSet.getInt(COLUMN_NAME_COUNT));

        return application;
    }

    private Collection<DetailApplication> selectAll(String query) throws DaoException {
        ArrayList<DetailApplication> applications = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                applications.add(fillApplication(resultSet));
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return applications;
    }
}
