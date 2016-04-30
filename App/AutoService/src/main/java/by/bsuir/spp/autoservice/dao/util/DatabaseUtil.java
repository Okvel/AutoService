package by.bsuir.spp.autoservice.dao.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseUtil
{
    private static final String INITIAL_CONTEXT = "initialContext";
    private static final String DATA_RESOURCE_NAME = "dataResourceName";

    private static ResourceBundle bundle = ResourceBundle.getBundle("database");

    public static Connection getConnection() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup(bundle.getString(INITIAL_CONTEXT));
        DataSource dataSource = (DataSource) context.lookup(bundle.getString(DATA_RESOURCE_NAME));

        return dataSource.getConnection();
    }
}
