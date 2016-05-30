package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.Act;
import by.bsuir.spp.autoservice.service.ActService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShowAcceptanceActListAction implements Action {
    private static Logger logger = Logger.getLogger(SignInAction.class);

    private List<Act> acts;
    @Override
    public String execute() throws Exception {
        String result = ERROR;
        try{
            ActService service = ActService.getInstance();
            acts = new ArrayList<>(service.findAllAcceptanceActs());
            result = SUCCESS;
        } catch (ServiceException ex){
            logger.error("show acceptance act list action error", ex);
        }
        return result;
    }

    public List<Act> getActs(){
        return acts;
    }
}
