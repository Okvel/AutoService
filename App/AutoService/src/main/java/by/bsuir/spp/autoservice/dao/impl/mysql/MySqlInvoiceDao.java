package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.InvoiceDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.Invoice;

import javax.naming.NamingException;
import java.sql.*;
import java.util.Collection;

public class MySqlInvoiceDao implements InvoiceDao {
    private static final String SQL_INSERT = "INSERT INTO invoice(manager_id, details_application_id) VALUES (?, ?)";

    private static MySqlInvoiceDao instance = new MySqlInvoiceDao();

    private MySqlInvoiceDao() {}

    public static MySqlInvoiceDao getInstance() {
        return instance;
    }

    @Override
    public Invoice findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Collection<Invoice> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long save(Invoice entity) throws DaoException {
        Long id = null;
        try (
                Connection connection = DatabaseUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)
                ) {
            statement.setLong(1, entity.getManager().getId());
            statement.setLong(2, entity.getApplication().getId());
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
