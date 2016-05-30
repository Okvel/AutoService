package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.CarDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Car;
import by.bsuir.spp.autoservice.entity.CarModel;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySqlCarDao implements CarDao {
    private static final String SQL_SELECT_ALL = "SELECT car.id, car_model.name, car_vendor.name, vin, registration_number " +
            "FROM car JOIN car_model ON model_id = car_model.id JOIN car_vendor ON vendor_id = car_vendor.id";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " WHERE car.id = ?";
    private static final String SQL_SELECT_BY_PARAMETERS = "SELECT car.id FROM car JOIN car_model ON model_id = " +
            "car_model.id  WHERE car_model.name = ? AND vin = ? AND registration_number = ?";
    private static final String SQL_INSERT = "INSERT INTO car(model_id, registration_number, vin) VALUES(?,?,?)";

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
        ArrayList<Car> cars = new ArrayList<>();
        try(
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement();
                ){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while(resultSet.next()){
                cars.add(fillCar(resultSet));
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return cars;
    }

    @Override
    public Long save(Car entity) throws DaoException {
        Long id = null;
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement checkStatement = connection.prepareStatement(SQL_SELECT_BY_PARAMETERS);
            PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
        ){
            checkStatement.setString(1, entity.getModel().getName());
            checkStatement.setString(2, entity.getVin());
            checkStatement.setString(3, entity.getRegistrationNumber());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getLong(COLUMN_NAME_CAR_ID);
            } else {
                MySqlCarModelDao carModelDao = MySqlCarModelDao.getInstance();
                insertStatement.setLong(1, carModelDao.save(entity.getModel()));
                insertStatement.setString(2, entity.getRegistrationNumber());
                insertStatement.setString(3, entity.getVin());
                if (insertStatement.executeUpdate() == 1) {
                    resultSet = insertStatement.getGeneratedKeys();
                    resultSet.next();
                    id = resultSet.getLong(1);
                }
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return id;
    }

    private Car fillCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(COLUMN_NAME_CAR_ID));
        car.setModel(new CarModel());
        car.getModel().setName(resultSet.getString(COLUMN_NAME_CAR_MODEL_NAME));
        car.getModel().setVendor(resultSet.getString(COLUMN_NAME_CAR_VENDOR_NAME));
        car.setRegistrationNumber(resultSet.getString(COLUMN_NAME_REGISTRATION_NUMBER));
        car.setVin(resultSet.getString(COLUMN_NAME_VIN));

        return car;
    }
}
