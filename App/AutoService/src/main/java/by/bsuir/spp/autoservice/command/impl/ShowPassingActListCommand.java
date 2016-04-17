package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.Act;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowPassingActListCommand implements BaseCommand {
    private static final String REQUEST_ATTRIBUTE_ACT_LIST_NAME = "acts";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        try {
            ActService service = ActService.getInstance();
            List<Act> acts = service.findAllPassingActs();
            request.setAttribute(REQUEST_ATTRIBUTE_ACT_LIST_NAME, acts);
        } catch (ServiceException ex) {
            throw new CommandException(ex);
        }

        return null;
    }
}
