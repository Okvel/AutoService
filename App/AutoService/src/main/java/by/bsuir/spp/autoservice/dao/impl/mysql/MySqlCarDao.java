package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.CarDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Car;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlCarDao implements CarDao {
    private static final String SQL_SELECT_ALL = "SELECT car.id, car_model.name, car_vendor.name, vin, registration_number " +
            "FROM car JOIN car_model ON model_id = car_model.id JOIN car_vendor ON vendor_id = car_vendor.id";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE car.id = ?";

    private static final String COLUMN_NAME_CAR_ID = "car.id";
    private static final String COLUMN_NAME_CAR_MODEL_NAME = "car_model.name";
    private static final String COLUMN_NAME_CAR_VENDOR_NAME = "car_vendor.name";
    private static final String COLUMN_NAME_VIN = "vin";
    private static final String COLUMN_NAME_REGISTRATION_NUMBER = "registration_number";

    private static MySqlCarDao instance = new MySqlCarDao();

    private MySqlCarDao() {}

    public static MySqlCarDao getInstance() {
        return instance;
    }

    @Override
    public Car findById(Long id) throws DaoException {
        Car car = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = fillCar(resultSet);
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return car;
    }

    @Override
    public Collection<Car> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Car entity) throws DaoException {
        return null;
    }

    private Car fillCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(COLUMN_NAME_CAR_ID));
        car.setVendor(resultSet.getString(COLUMN_NAME_CAR_VENDOR_NAME));
        car.setModel(resultSet.getString(COLUMN_NAME_CAR_MODEL_NAME));
        car.setRegistrationNumber(resultSet.getString(COLUMN_NAME_REGISTRATION_NUMBER));
        car.setVin(resultSet.getString(COLUMN_NAME_VIN));

        return car;
    }
}
