package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.service.RepairReportService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

public class ShowRepairReportAction implements Action {
    private static Logger logger = Logger.getLogger(ShowRepairReportAction.class);

    private RepairReport report;
    private Long id;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try{
            RepairReportService service = RepairReportService.getInstance();
            report = service.findById(id);
            result = SUCCESS;
        } catch (ServiceException ex){
            logger.error(ex);
        }

        return result;
    }

    public RepairReport getReport() {
        return report;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
