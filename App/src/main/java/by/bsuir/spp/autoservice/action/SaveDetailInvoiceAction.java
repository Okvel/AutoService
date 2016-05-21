package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.DetailApplication;
import by.bsuir.spp.autoservice.entity.Invoice;
import by.bsuir.spp.autoservice.entity.User;
import by.bsuir.spp.autoservice.service.InvoiceService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class SaveDetailInvoiceAction implements Action {
    private static final String SESSION_ATTRIBUTE_NAME_ID = "id";
    private static Logger logger = Logger.getLogger(SaveDetailInvoiceAction.class);

    private Invoice invoice = new Invoice();

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        User manager = new User();
        HttpSession session = ServletActionContext.getRequest() .getSession();
        manager.setId((Long)session.getAttribute(SESSION_ATTRIBUTE_NAME_ID));
        invoice.setManager(manager);
        try{
            InvoiceService service = InvoiceService.getInstance();
            if (service.save(invoice)){
                result = SUCCESS;
            }
        } catch (ServiceException ex) {
            logger.error("Save detail invoice action error", ex);
        }
        return result;
    }

    public void setDetailApplication(Long id){
        DetailApplication detailApplication = new DetailApplication();
        detailApplication.setId(id);
        invoice.setApplication(detailApplication);
    }
}
