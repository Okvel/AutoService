package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.service.RepairReportService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Рылеев on 29.05.2016.
 */
public class ShowRepairReportCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_NAME_ID = "id";
    private static final String REQUEST_ATTRIBUTE_NAME_REPAIR_REPORT = "repair_report";
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        PagePath page = null;
        try{
            RepairReportService service = RepairReportService.getInstance();
            RepairReport repairReport = service.findById(Long.parseLong(request.getParameter(REQUEST_PARAMETER_NAME_ID)));
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_REPAIR_REPORT, repairReport);
            page = PagePath.REPAIR_REPORTS;
        } catch (ServiceException ex){

        }
        return page;
    }
}
