package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.service.DetailApplicationService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Рылеев on 29.05.2016.
 */
public class ShowDetailOrderCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_NAME_ID = "id";
    private static final String REQUEST_ATTRIBUTE_NAME_ORDER = "detail_order";
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        PagePath page = null;
        try{
            DetailApplicationService service = DetailApplicationService.getInstance();
            DetailApplication application = service.findById(Long.parseLong(request.getParameter(REQUEST_PARAMETER_NAME_ID)));
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_ORDER, application);
            page = PagePath.DETAIL_ORDERS;
        } catch (ServiceException ex){
            throw new CommandException(ex);
        }
        return page;
    }
}
