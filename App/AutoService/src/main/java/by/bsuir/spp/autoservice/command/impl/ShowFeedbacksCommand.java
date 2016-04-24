package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.Feedback;
import by.bsuir.spp.autoservice.service.FeedbackService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class ShowFeedbacksCommand implements BaseCommand {
    private static final String REQUEST_ATTRIBUTE_FEEDBACK_LIST_NAME = "feedbacks";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        ArrayList<Feedback> feedbacks;
        try {
            FeedbackService service = FeedbackService.getInstance();
            feedbacks = service.findAll();
            request.setAttribute(REQUEST_ATTRIBUTE_FEEDBACK_LIST_NAME, feedbacks);
        } catch (ServiceException ex){
            throw new CommandException(ex);
        }
        return null;
    }
}
