package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.Feedback;
import by.bsuir.spp.autoservice.service.FeedbackService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ShowFeedbacksAction implements Action {
    private static Logger logger = Logger.getLogger(ShowFeedbacksAction.class);

    private ArrayList<Feedback> feedbacks;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try {
            FeedbackService service = FeedbackService.getInstance();
            feedbacks = service.findAll();
            result = SUCCESS;
        } catch (ServiceException ex){
            logger.error("Show feedback page error", ex);
        }

        return result;
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
