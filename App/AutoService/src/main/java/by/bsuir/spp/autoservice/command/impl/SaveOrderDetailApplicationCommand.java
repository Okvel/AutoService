package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.command.util.DetailApplicationValidator;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.service.DetailApplicationService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveOrderDetailApplicationCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_TEXT = "text";
    private static final String SUCCESS_MESSAGE = "Order was successfully added";
    private static final String FAIL_MESSAGE = "Order wasn't added";
    private static final String ERROR_MESSAGE = "Some fields contains wrong information";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        DetailApplication application = DetailApplicationValidator.validate(request);
        if (application != null) {
            try {
                DetailApplicationService service = DetailApplicationService.getInstance();
                if (service.save(application)) {
                    request.setAttribute(REQUEST_PARAMETER_TEXT, SUCCESS_MESSAGE);
                } else {
                    request.setAttribute(REQUEST_PARAMETER_TEXT, FAIL_MESSAGE);
                }
            } catch (ServiceException ex) {
                throw new CommandException(ex);
            }
        } else {
            request.setAttribute(REQUEST_PARAMETER_TEXT, ERROR_MESSAGE);
        }

        return PagePath.MESSAGE;
    }
}
