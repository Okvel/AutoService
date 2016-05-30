package by.bsuir.spp.autoservice.action;

import by.bsuir.spp.autoservice.entity.Car;
import by.bsuir.spp.autoservice.service.CarService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShowAddRepairReportAction implements Action {
    private static Logger logger = Logger.getLogger(ShowAddRepairReportAction.class);

    private List<Car> cars;

    @Override
    public String execute() throws Exception {
        String result = ERROR;

        try{
            CarService service = CarService.getInstance();
            cars = new ArrayList<>(service.findAll());
            result = SUCCESS;
        } catch (ServiceException ex){
            logger.error("Show repair report form error", ex);
        }

        return result;
    }

    public List<Car> getCars() {
        return cars;
    }
}
