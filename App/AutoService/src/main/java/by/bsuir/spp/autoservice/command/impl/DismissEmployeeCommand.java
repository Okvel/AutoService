package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DismissEmployeeCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_EMPLOYEE_ID_NAME = "employee_id";
    private static final String REQUEST_PARAMETER_TEXT = "text";
    private static final String SUCCESS_MESSAGE = "Employee was successfully dismissed";
    private static final String FAIL_MESSAGE = "Employee wasn't dismissed";
    private static final String ERROR_MESSAGE = "Such employee aren't exist";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        String employeeId = request.getParameter(REQUEST_PARAMETER_EMPLOYEE_ID_NAME);
        if (employeeId != null) {
            Long id = Long.parseLong(employeeId);
            UserService service = UserService.getInstance();
            try {
                if (service.deleteById(id)) {
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
