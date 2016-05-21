package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.service.DetailApplicationService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class ShowFillDetailInvoiceAction implements Action{
    private ArrayList<DetailApplication> applications;
    private static Logger logger = Logger.getLogger(ShowFillDetailInvoiceAction.class);

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try{
            DetailApplicationService service = DetailApplicationService.getInstance();
            applications = new ArrayList<>(service.findAllFreeApplications());
            result = SUCCESS;
        } catch (ServiceException ex){
            logger.error("Show fill detail invoice action error", ex);
        }
        return result;
    }

    public ArrayList<DetailApplication> getApplications(){
        return applications;
    }
}
