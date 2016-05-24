package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.action.util.RepairReportValidator;
import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.service.RepairReportService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class SaveRepairReportAction implements Action {
    private static final String SAVE_ERROR = "save_error";
    private static final String VALIDATE_ERROR_MESSAGE = "You must set start and end repair dates";
    private static final String SAVE_ERROR_MESSAGE = "Report wasn't saved. Please contact your system administrator";
    private static final String SESSION_ATTRIBUTE_ID = "id";

    private static Logger logger = Logger.getLogger(SaveRepairReportAction.class);

    private String carId;
    private String startDate;
    private String endDate;
    private String description;
    private String message;

    @Override
    public String execute() throws Exception {
        String result;
        Long mechanicId = (Long) ServletActionContext.getRequest().getSession().getAttribute(SESSION_ATTRIBUTE_ID);
        RepairReport report = RepairReportValidator.validate(mechanicId, Long.parseLong(carId), startDate, endDate, description);
        if (report != null) {
            try {
                RepairReportService service = RepairReportService.getInstance();
                if (service.save(report)) {
                    result = SUCCESS;
                } else {
                    result = SAVE_ERROR;
                    message = SAVE_ERROR_MESSAGE;
                }
            } catch (ServiceException ex) {
                logger.error("Save repair report action error", ex);
                result = ERROR;
            }
        } else {
            result = SAVE_ERROR;
            message = VALIDATE_ERROR_MESSAGE;
        }

        return result;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }
}
