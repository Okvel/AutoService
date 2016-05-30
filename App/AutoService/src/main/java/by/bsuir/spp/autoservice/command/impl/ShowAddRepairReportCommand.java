package by.bsuir.spp.autoservice.command.impl;

import by.bsuir.spp.autoservice.command.BaseCommand;
import by.bsuir.spp.autoservice.command.CommandException;
import by.bsuir.spp.autoservice.command.PagePath;
import by.bsuir.spp.autoservice.entity.Car;
import by.bsuir.spp.autoservice.service.CarService;
import by.bsuir.spp.autoservice.service.ServiceException;
import com.sun.javafx.geom.AreaOp;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Рылеев on 30.05.2016.
 */
public class ShowAddRepairReportCommand implements BaseCommand {
    private static final String REQUEST_ATTRIBUTE_NAME_CARS = "cars";
    @Override
    public PagePath execute(HttpServletRequest request) throws CommandException {
        PagePath page = null;
        try{
            CarService service = CarService.getInstance();
            ArrayList<Car> cars = new ArrayList<>(service.findAll());
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_CARS, cars);
            page = PagePath.ADD_REPAIR_REPORT;
        } catch (ServiceException ex){
            throw new CommandException(ex);
        }

        return page;
    }
}
