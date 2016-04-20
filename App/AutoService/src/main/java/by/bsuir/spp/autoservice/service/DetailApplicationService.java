package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.entity.DetailApplication;

public class DetailApplicationService extends BaseService {
    private static DetailApplicationService instance = new DetailApplicationService();

    private DetailApplicationService() {}

    public static DetailApplicationService getInstance() {
        return instance;
    }

    public boolean save(DetailApplication application) throws ServiceException {
        boolean result = false;

        return result;
    }
}
