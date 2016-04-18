package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RepairReportDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.RepairReport;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MySqlRepairReportDao implements RepairReportDao {
    private static final String SQL_SELECT_ALL = "SELECT id, car_id, mechanic_id, start_date, end_date, description " +
            "FROM repair_report";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO repair_report(car_id, mechanic_id, start_date, end_date, description) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String COLUMN_NAME_CAR_ID = "car_id";
    private static final String COLUMN_NAME_MECHANIC_ID = "mechanic_id";
    private static final String COLUMN_NAME_START_DATE = "start_date";
    private static final String COLUMN_NAME_END_DATE = "end_date";
    private static final String COLUMN_NAME_DESCRIPTION = "description";

    private static MySqlRepairReportDao instance = new MySqlRepairReportDao();

    private MySqlRepairReportDao() {}

    public static MySqlRepairReportDao getInstance() {
        return instance;
    }

    @Override
    public RepairReport findById(Long id) throws DaoException {
        RepairReport report = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                report = fillReport(resultSet);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return report;
    }

    @Override
    public Collection<RepairReport> findAll() throws DaoException {
        ArrayList<RepairReport> reports = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)
                ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reports.add(fillReport(resultSet));
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }
        return reports;
    }

    @Override
    public Long save(RepairReport entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
                ) {
            statement.setLong(1, entity.getCar().getId());
            statement.setLong(2, entity.getMechanic().getId());
            statement.setDate(3, (Date) entity.getStartDate());
            statement.setDate(4, (Date) entity.getEndDate());
            statement.setString(5, entity.getDescription());
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

    private RepairReport fillReport(ResultSet resultSet) throws DaoException, SQLException {
        MySqlCarDao carDao = MySqlCarDao.getInstance();
        MySqlUserDao userDao = MySqlUserDao.getInstance();
        RepairReport report = new RepairReport();
        report.setId(resultSet.getLong(COLUMN_NAME_ID));
        report.setCar(carDao.findById(resultSet.getLong(COLUMN_NAME_CAR_ID)));
        report.setMechanic(userDao.findById(resultSet.getLong(COLUMN_NAME_MECHANIC_ID)));
        report.setStartDate(resultSet.getDate(COLUMN_NAME_START_DATE));
        report.setEndDate(resultSet.getDate(COLUMN_NAME_END_DATE));
        report.setDescription(resultSet.getString(COLUMN_NAME_DESCRIPTION));

        return report;
    }
}
