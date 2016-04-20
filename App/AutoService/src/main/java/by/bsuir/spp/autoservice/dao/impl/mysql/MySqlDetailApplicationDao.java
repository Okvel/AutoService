package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.DetailApplicationDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.DetailApplication;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlDetailApplicationDao implements DetailApplicationDao {
    private static final String SQL_INSERT = "INSERT INTO details_application(mechanic_id, detail_id, count) VALUES (?,?,?)";

    private static MySqlDetailApplicationDao instance = new MySqlDetailApplicationDao();

    private MySqlDetailApplicationDao() {}

    public static MySqlDetailApplicationDao getInstance() {
        return instance;
    }

    @Override
    public DetailApplication findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<DetailApplication> findAll() throws DaoException {
        return null;
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
}
