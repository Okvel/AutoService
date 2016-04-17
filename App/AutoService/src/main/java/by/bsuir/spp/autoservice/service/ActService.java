package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.ActDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.entity.Act;

import java.util.ArrayList;
import java.util.List;

public class ActService extends BaseService {
    private static ActService instance = new ActService();
    private static ActDao dao = factory.getActDao();

    private ActService() {}

    public static ActService getInstance() {
        return instance;
    }

    public List<Act> findAllPassingActs() throws ServiceException {
        ArrayList<Act> acts;
        try {
            acts = new ArrayList<>(dao.findAllPassingActs());
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return acts;
    }
}
