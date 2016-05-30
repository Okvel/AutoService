package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.DocumentFormat;
import by.bsuir.spp.autoservice.service.DocumentService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

public class DownloadOrderDetailsApplicationAction implements Action {
    private static Logger logger = Logger.getLogger(DownloadOrderDetailsApplicationAction.class);

    private String link;
    private String format;


    @Override
    public String execute() {
        String result = ERROR;
        DocumentService service = DocumentService.getInstance();
        try {
            DocumentFormat format = DocumentFormat.valueOf(this.format);
            String link = service.getOrderDetailsApplication(format);
            this.link = link;
            result = SUCCESS;
        } catch (ServiceException ex) {
            logger.error("Download detail order error", ex);
        }
        return result;
    }


    public String getLink() {
        return link;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
