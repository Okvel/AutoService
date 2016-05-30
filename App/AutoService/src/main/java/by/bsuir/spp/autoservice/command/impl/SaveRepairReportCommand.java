package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.command.util.RepairReportValidator;
import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.service.RepairReportService;
import by.bsuir.spp.autoservice.service.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class SaveRepairReportCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_TEXT = "text";
    private static final String SUCCESS_MESSAGE = "Report was successfully added";
    private static final String FAIL_MESSAGE = "Report wasn't added";
    private static final String ERROR_MESSAGE = "Some fields contains wrong information";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        RepairReport report = RepairReportValidator.validate(request);
        if (report != null) {
            try {
                RepairReportService service = RepairReportService.getInstance();
                if (service.save(report)) {
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
