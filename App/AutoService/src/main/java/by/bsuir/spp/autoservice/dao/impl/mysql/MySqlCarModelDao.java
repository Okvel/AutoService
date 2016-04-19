package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.ModelDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.CarModel;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Рылеев on 18.04.2016.
 */
public class MySqlCarModelDao implements ModelDao {
    private static final String SQL_SELECT_BY_NAME = "SELECT car_model.id FROM car_model WHERE name = ?";
    private static final String SQL_INSERT = "INSERT INTO car_model(name, vendor_id) VALUES(?,?)";
    private static final String COLUMN_NAME_MODEL_ID = "car_model.id";

    private static MySqlCarModelDao instance = new MySqlCarModelDao();

    private MySqlCarModelDao(){}

    public static MySqlCarModelDao getInstance() {
        return instance;
    }

    @Override
    public CarModel findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<CarModel> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(CarModel entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement checkStatement = connection.prepareStatement(SQL_SELECT_BY_NAME);
                PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
        ){
            checkStatement.setString(1, entity.getName());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getLong(COLUMN_NAME_MODEL_ID);
            } else {
                MySqlCarVendorDao carVendorDao = MySqlCarVendorDao.getInstance();
                insertStatement.setString(1, entity.getName());
                insertStatement.setLong(2, carVendorDao.save(entity.getVendor()));
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
}
