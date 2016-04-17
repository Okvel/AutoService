package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RepairReportDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.RepairReport;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MySqlRepairReportDao implements RepairReportDao {
    private static final String SQL_SELECT_ALL = "SELECT id, car_id, mechanic_id, start_date, end_date, description " +
            "FROM repair_report";

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
        return null;
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
        return null;
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
