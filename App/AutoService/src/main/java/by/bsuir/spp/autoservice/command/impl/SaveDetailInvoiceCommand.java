package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.entity.Invoice;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.InvoiceService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveDetailInvoiceCommand implements BaseCommand {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static final String PARAMETER_NAME_APPLICATION_ID = "app_id";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        User manager = new User();
        manager.setId((Long) request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_ID));
        DetailApplication application = new DetailApplication();
        application.setId(Long.parseLong(request.getParameter(PARAMETER_NAME_APPLICATION_ID)));
        Invoice invoice = new Invoice();
        invoice.setManager(manager);
        invoice.setApplication(application);
        try {
            InvoiceService service = InvoiceService.getInstance();
            if (service.save(invoice)) {
                // set success page
            } else {
                // set fail page
            }
        } catch (ServiceException ex) {
            throw new CommandException(ex);
        }

        return null;
    }
}
