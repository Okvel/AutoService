package by.bsuir.spp.autoservice.dao.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil
{
    public static Connection getConnection() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");

        String dataResourceName = "jdbc/sppautoservice";
        DataSource dataSource = (DataSource) context.lookup(dataResourceName);

        return dataSource.getConnection();
    }
}
