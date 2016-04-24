package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.entity.Client;
import by.bsuir.spp.autoservice.entity.Feedback;
import by.bsuir.spp.autoservice.service.FeedbackService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveFeedbackCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_NAME_LAST_NAME = "last_name";
    private static final String REQUEST_PARAMETER_NAME_FIRST_NAME = "first_name";
    private static final String REQUEST_PARAMETER_NAME_TEXT = "text";
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        PagePath page = null;
        Feedback feedback = new Feedback();
        feedback.setFirstName(request.getParameter(REQUEST_PARAMETER_NAME_FIRST_NAME));
        feedback.setLastName(request.getParameter(REQUEST_PARAMETER_NAME_LAST_NAME));
        feedback.setText(request.getParameter(REQUEST_PARAMETER_NAME_TEXT));

        try{
            FeedbackService service = FeedbackService.getInstance();
            if (service.save(feedback)){
                //set success page
            } else {
                //set fail page
            }
        } catch (ServiceException ex){
            throw new CommandException(ex);
        }
        return page;
    }
}
