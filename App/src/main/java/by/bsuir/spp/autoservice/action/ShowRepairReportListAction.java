package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.service.RepairReportService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.List;

public class ShowRepairReportListAction implements Action {
    private static Logger logger = Logger.getLogger(ShowRepairReportListAction.class);

    private List<RepairReport> reports;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try {
            RepairReportService service = RepairReportService.getInstance();
            reports = service.findAll();
            result = SUCCESS;
        } catch (ServiceException ex) {
            logger.error("Show repair report list error", ex);
        }

        return result;
    }

    public List<RepairReport> getReports() {
        return reports;
    }
}
