package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.factory.DaoFactory;

abstract class BaseService {
    protected static DaoFactory factory = DaoFactory.takeDaoFactory();
}
