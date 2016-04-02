package by.bsuir.spp.autoservice.dao.factory.impl;

import by.bsuir.spp.autoservice.dao.UserDao;
import by.bsuir.spp.autoservice.dao.factory.DaoFactory;
import by.bsuir.spp.autoservice.dao.impl.mysql.MySqlUserDao;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance = new MySqlDaoFactory();

    private MySqlDaoFactory() {}

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return MySqlUserDao.getInstance();
    }
}
