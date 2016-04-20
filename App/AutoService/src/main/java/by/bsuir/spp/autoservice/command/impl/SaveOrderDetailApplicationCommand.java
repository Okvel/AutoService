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
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        DetailApplication application = DetailApplicationValidator.validate(request);
        if (application != null) {
            try {
                DetailApplicationService service = DetailApplicationService.getInstance();
                if (service.save(application)) {
                    // set success page
                } else {
                    //set fail page
                }
            } catch (ServiceException ex) {
                throw new CommandException(ex);
            }
        } else {
            //set error page
        }

        return null;
    }
}
