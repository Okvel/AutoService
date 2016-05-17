package by.bsuir.spp.autoservice.dao.impl.mysql;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.InvoiceDao;
import by.bsuir.spp.autoservice.dao.util.DatabaseUtil;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.entity.Invoice;
import by.bsuir.spp.autoservice.entity.User;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MySqlInvoiceDao implements InvoiceDao {
    private static final String SQL_SELECT_ALL = "SELECT invoice.id, manager_id, details_application_id FROM invoice";
    private static final String SQL_INSERT = "INSERT INTO invoice(manager_id, details_application_id) VALUES (?, ?)";

    private static final String COLUMN_NAME_INVOICE_ID = "invoice.id";
    private static final String COLUMN_NAME_MANAGER_ID = "manager_id";
    private static final String COLUMN_NAME_APPLICATION_ID = "details_application_id";

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
        ArrayList<Invoice> invoices = new ArrayList<>();
        try (
                Connection connection = DatabaseUtil.getConnection();
                Statement statement = connection.createStatement()
                ) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                invoices.add(fillInvoice(resultSet));
            }
        } catch (SQLException | NamingException ex) {
            throw new DaoException(ex);
        }

        return invoices;
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

    private Invoice fillInvoice(ResultSet resultSet) throws SQLException, DaoException {
        MySqlUserDao userDao = MySqlUserDao.getInstance();
        MySqlDetailApplicationDao applicationDao = MySqlDetailApplicationDao.getInstance();
        User manager = userDao.findById(resultSet.getLong(COLUMN_NAME_MANAGER_ID));
        DetailApplication detailApplication = applicationDao.findById(resultSet.getLong(COLUMN_NAME_APPLICATION_ID));
        Invoice invoice = new Invoice();
        invoice.setId(resultSet.getLong(COLUMN_NAME_INVOICE_ID));
        invoice.setManager(manager);
        invoice.setApplication(detailApplication);

        return invoice;
    }
}
