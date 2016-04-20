package by.bsuir.spp.autoservice.dao.factory.impl;

import by.bsuir.spp.autoservice.dao.*;
import by.bsuir.spp.autoservice.dao.factory.DaoFactory;
import by.bsuir.spp.autoservice.dao.impl.mysql.*;

public class MySqlDaoFactory extends DaoFactory {
    private static MySqlDaoFactory instance = new MySqlDaoFactory();

    private MySqlDaoFactory() {}

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return MySqlUserDao.getInstance();
    }

    @Override
    public CarDao getCarDao() {
        return MySqlCarDao.getInstance();
    }

    @Override
    public RepairReportDao getRepairReportDao() {
        return MySqlRepairReportDao.getInstance();
    }

    @Override
    public ActDao getActDao() {
        return MySqlActDao.getInstance();
    }

    @Override
    public DetailApplicationDao getDetailApplicationDao() {
        return MySqlDetailApplicationDao.getInstance();
    }
}
