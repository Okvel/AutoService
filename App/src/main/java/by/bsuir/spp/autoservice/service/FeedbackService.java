package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.FeedbackDao;
import by.bsuir.spp.autoservice.entity.Feedback;
import by.bsuir.spp.autoservice.service.util.ServiceUtil;

import java.util.ArrayList;

/**
 * Created by Рылеев on 24.04.2016.
 */
public class FeedbackService extends BaseService {
    private static FeedbackService instance = new FeedbackService();
    private static FeedbackDao dao = factory.getFeedbackDao();

    private FeedbackService(){}

    public static FeedbackService getInstance() {
        return instance;
    }

    public ArrayList<Feedback> findAll() throws ServiceException{
        ArrayList<Feedback> feedbacks;
        try {
            feedbacks = new ArrayList<>(dao.findAll());
        } catch (DaoException ex){
            throw new ServiceException(ex);
        }

        return feedbacks;
    }

    public boolean save(Feedback feedback) throws ServiceException{
        return ServiceUtil.save(feedback, dao);
    }
}
