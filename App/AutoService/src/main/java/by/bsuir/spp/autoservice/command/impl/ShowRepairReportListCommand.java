package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.service.RepairReportService;
import by.bsuir.spp.autoservice.service.ServiceException;
 
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowRepairReportListCommand implements BaseCommand {
    private static final String REQUEST_ATTRIBUTE_NAME_REPORTS = "reports";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        try {
            RepairReportService service = RepairReportService.getInstance();
            List<RepairReport> reports = service.findAll();
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_REPORTS, reports);
        } catch (ServiceException ex) {
            throw new CommandException(ex);
        }

        return null;
    }
}
