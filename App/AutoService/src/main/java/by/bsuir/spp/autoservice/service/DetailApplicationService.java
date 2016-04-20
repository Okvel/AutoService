package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.DetailApplicationDao;
import by.bsuir.spp.autoservice.entity.DetailApplication;

public class DetailApplicationService extends BaseService {
    private static DetailApplicationService instance = new DetailApplicationService();
    private static DetailApplicationDao dao = factory.getDetailApplicationDao();

    private DetailApplicationService() {}

    public static DetailApplicationService getInstance() {
        return instance;
    }

    public boolean save(DetailApplication application) throws ServiceException {
        boolean result = false;
        try {
            if (dao.save(application) != null) {
                result = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return result;
    }
}