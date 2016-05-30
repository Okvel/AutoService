package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.DetailDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Detail;
import org.apache.poi.ss.formula.functions.Na;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

class MySqlDetailDao implements DetailDao {
    private static final String SQL_SELECT_BY_NAME = "SELECT id FROM detail WHERE name = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name FROM detail WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO detail(name) VALUE (?)";

    private static final String COLUMN_NAME_NAME = "name";

    private static MySqlDetailDao instance = new MySqlDetailDao();

    private MySqlDetailDao() {}

    public static MySqlDetailDao getInstance() {
        return instance;
    }

    @Override
    public Detail findById(Long id) throws DaoException {
        Detail detail = null;
        try(
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)
                ){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                detail = fillDetail(resultSet);
            }
        } catch (SQLException | NamingException ex){
            throw new DaoException(ex);
        }
        return detail;
    }

    @Override
    public Collection<Detail> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Detail entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement checkStatement = connection.prepareStatement(SQL_SELECT_BY_NAME);
                PreparedStatement insertStatement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
                ) {
            checkStatement.setString(1, entity.getName());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(COLUMN_NAME_ID);
            } else {
                insertStatement.setString(1, entity.getName());
                if (insertStatement.executeUpdate() == 1) {
                    resultSet = insertStatement.getGeneratedKeys();
                    resultSet.next();
                    id = resultSet.getLong(1);
                }
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return id;
    }

    private Detail fillDetail(ResultSet resultSet) throws SQLException{
        Detail detail = new Detail();
        detail.setId(resultSet.getLong(COLUMN_NAME_ID));
        detail.setName(resultSet.getString(COLUMN_NAME_NAME));
        return detail;
    }
}
