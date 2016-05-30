package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.Act;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

public class ShowActAction implements Action {
    private static Logger logger = Logger.getLogger(ShowActAction.class);

    private long actId;
    private Act act;

    @Override
    public String execute() throws Exception {
        String result = ERROR;
        ActService service = ActService.getInstance();
        try {
            act = service.findById(actId);
            if (act != null) {
                result = SUCCESS;
            }
        } catch (ServiceException ex) {
            logger.error("Show act action error", ex);
        }

        return result;
    }

    public void setId(long actId) {
        this.actId = actId;
    }

    public Act getAct() {
        return act;
    }
}
