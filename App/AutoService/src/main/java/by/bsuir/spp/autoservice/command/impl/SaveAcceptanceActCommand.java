package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.command.util.AcceptanceActValidator;
import by.bsuir.spp.autoservice.entity.Act;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveAcceptanceActCommand implements BaseCommand {
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        Act act = AcceptanceActValidator.validate(request);
        if (act != null) {
            try {
                ActService service = ActService.getInstance();
                if (service.save(act)) {
                    // set successful page
                } else {
                    // set fail page
                }
            } catch (ServiceException ex) {
                throw new CommandException(ex);
            }
        } else {
            // set error page
        }

        return null;
    }
}
