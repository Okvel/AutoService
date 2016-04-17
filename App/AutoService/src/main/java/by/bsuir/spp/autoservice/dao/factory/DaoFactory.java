package by.bsuir.spp.autoservice.dao.factory;

import by.bsuir.spp.autoservice.dao.*;
import by.bsuir.spp.autoservice.dao.factory.impl.MySqlDaoFactory;
import by.bsuir.spp.autoservice.entity.RepairReport;
import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class DaoFactory {
    private static Logger logger = Logger.getLogger(DaoFactory.class);

    private final static String DAO_TYPE = readDaoType();
    private final static String MYSQL_DAO_TYPE = "mysql";
    private final static String PROPERTY_DAO_FILE = "application";
    private final static String DAO_TYPE_KEY = "dao.type";

    private static String readDaoType() {
        ResourceBundle bundle;
        String result = MYSQL_DAO_TYPE;
        try {
            bundle = ResourceBundle.getBundle(PROPERTY_DAO_FILE);
            result = bundle.getString(DAO_TYPE_KEY);
        } catch (MissingResourceException ex) {
            logger.error("Resource file not found", ex);
        }

        return result;
    }

    public static DaoFactory takeDaoFactory() {
        DaoFactory factory;
        switch (DAO_TYPE) {
            case MYSQL_DAO_TYPE:
                // Falls through
            default:
                factory = MySqlDaoFactory.getInstance();
        }

        return factory;
    }

    public abstract UserDao getUserDao();
    public abstract PersonDao getPersonDao();
    public abstract UserCredentialsDao getUserCredentialsDao();
    public abstract CarDao getCarDao();
    public abstract RepairReportDao getRepairReportDao();
    public abstract ActDao getActDao();
}
