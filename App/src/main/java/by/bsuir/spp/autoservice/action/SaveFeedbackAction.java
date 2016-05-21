package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.Feedback;
import by.bsuir.spp.autoservice.service.FeedbackService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.itextpdf.text.ExceptionConverter;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;


public class SaveFeedbackAction implements Action {
    private static Logger logger = Logger.getLogger(SignInAction.class);
    private Feedback feedback = new Feedback();

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try{
            FeedbackService service = FeedbackService.getInstance();
            if (service.save(feedback)){
                result = SUCCESS;
            }
        } catch (ServiceException ex){
            logger.error("Feedback adding action error", ex);
        }
        return result;
    }

    public void setFirstName(String firstName){
        feedback.setFirstName(firstName);
    }

    public void setLastName(String lastName){
        feedback.setLastName(lastName);
    }

    public void setText(String text){
        feedback.setText(text);
    }
}
