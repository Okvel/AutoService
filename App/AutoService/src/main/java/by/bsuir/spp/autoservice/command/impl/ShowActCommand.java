package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.Act;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class ShowActCommand implements BaseCommand {
    private static final String REQUEST_ATTRIBUTE_ACT_NAME = "act";
    private static final String REQUEST_PARAMETER_ACT_ID_NAME = "act_id";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        String page;
        String actId = request.getParameter(REQUEST_PARAMETER_ACT_ID_NAME);
        if (actId != null) {
            ActService service = ActService.getInstance();
            Long id = Long.parseLong(actId);
            try {
                Act act = service.findById(id);
                if (act != null) {
                    request.setAttribute(REQUEST_ATTRIBUTE_ACT_NAME, act);
                    //set success page
                } else {
                    //set fail page
                }
            } catch (ServiceException ex) {
                throw new CommandException(ex);
            }
        }
        return null;
    }
}
