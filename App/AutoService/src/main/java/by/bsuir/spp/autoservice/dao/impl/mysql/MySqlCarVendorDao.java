package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.VendorDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Рылеев on 18.04.2016.
 */
public class MySqlCarVendorDao implements VendorDao {
    private static final String SQL_SELECT_BY_NAME = "SELECT car_vendor.id FROM car_vendor WHERE name = ?";
    private static final String SQL_INSERT = "INSERT INTO car_vendor(name) VALUES(?)";
    private static final String COLUMN_NAME_VENDOR_ID = "car_vendor.id";

    private static MySqlCarVendorDao instance = new MySqlCarVendorDao();

    private MySqlCarVendorDao(){}

    public static MySqlCarVendorDao getInstance() {
        return instance;
    }

    @Override
    public String findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<String> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(String entity) throws DaoException {
        Long id = null;
        try(
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement checkStatement = connection.prepareStatement(SQL_SELECT_BY_NAME);
            PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT)
            ){
            checkStatement.setString(1, entity);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getLong(COLUMN_NAME_VENDOR_ID);
            } else {
                insertStatement.setString(1, entity);
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
