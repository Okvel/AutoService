package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.DocumentFormat;
import by.bsuir.spp.autoservice.service.DocumentService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

public class DownloadAcceptanceActAction implements Action {
    private static Logger logger = Logger.getLogger(DownloadAcceptanceActAction.class);

    private String link;
    private DocumentFormat documentFormat;

    @Override
    public String execute() throws Exception {
        DocumentService service = DocumentService.getInstance();
        try {
            link = service.getAcceptanceActDocument(documentFormat);
        } catch (ServiceException ex) {
            logger.error("Download act action error", ex);
        }
        return SUCCESS;
    }

    public String getLink() {
        return link;
    }

    public void setFormat(String format) {
        documentFormat = DocumentFormat.valueOf(format.toUpperCase());
    }
}
