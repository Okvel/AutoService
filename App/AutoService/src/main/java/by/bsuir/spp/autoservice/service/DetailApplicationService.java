package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.DetailApplicationDao;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.service.util.ServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailApplicationService extends BaseService {
    private static DetailApplicationService instance = new DetailApplicationService();
    private static DetailApplicationDao dao = factory.getDetailApplicationDao();

    private DetailApplicationService() {}

    public static DetailApplicationService getInstance() {
        return instance;
    }

    public boolean save(DetailApplication application) throws ServiceException {
        return ServiceUtil.save(application, dao);
    }

    public List<DetailApplication> findAllFreeApplications() throws ServiceException {
        List<DetailApplication> applications;
        try {
            applications = new ArrayList<>(dao.findAll());
            List<DetailApplication> processingApplications = new ArrayList<>(dao.findAllProcessing());
            applications.removeAll(processingApplications);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return applications;
    }

    public List<DetailApplication> findAll() throws ServiceException {
        List<DetailApplication> applications;
        try {
            applications = new ArrayList<>(dao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return applications;
    }
    public DetailApplication findById(Long id) throws ServiceException {
        DetailApplication application;
        try {
            application = dao.findById(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return application;
    }
}
