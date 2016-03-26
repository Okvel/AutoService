package by.bsuir.spp.autoservice.dao.factory.impl;

import by.bsuir.spp.autoservice.dao.factory.DaoFactory;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance = new MySqlDaoFactory();

    private MySqlDaoFactory() {}

    public static MySqlDaoFactory getInstance() {
        return instance;
    }
}
