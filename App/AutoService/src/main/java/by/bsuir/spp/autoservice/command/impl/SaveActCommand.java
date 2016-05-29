package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.command.util.ActValidator;
import by.bsuir.spp.autoservice.entity.Act;
import by.bsuir.spp.autoservice.entity.ActType;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveActCommand implements BaseCommand {
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        PagePath page = null;
        Act act = ActValidator.validate(request);
        if (act != null) {
            try {
                ActService service = ActService.getInstance();
                if (service.save(act)) {
                    if (act.getType() == ActType.ACCEPTANCE){
                        page = PagePath.ACCEPTANCE_ACTS;
                    } else {
                        page = PagePath.PASSING_ACTS;
                    }
                } else {
                    // set fail page
                }
            } catch (ServiceException ex) {
                throw new CommandException(ex);
            }
        } else {
            //set error page
        }

        return page;
    }
}
