package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.service.ServiceException;
import by.bsuir.spp.autoservice.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DismissEmployeeCommand implements BaseCommand {
    private static final String REQUEST_PARAMETER_EMPLOYEE_ID_NAME = "employee_id";

    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        String page;
        String employeeId = request.getParameter(REQUEST_PARAMETER_EMPLOYEE_ID_NAME);
        if (employeeId != null) {
            Long id = Long.parseLong(employeeId);
            UserService service = UserService.getInstance();
            try {
                if (service.deleteById(id)) {
                    // set success page
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
