package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.CarDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.entity.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Рылеев on 30.05.2016.
 */
public class CarService extends BaseService {
    private CarDao dao = factory.getCarDao();
    private static CarService instance = new CarService();

    private CarService(){}

    public static CarService getInstance() {
        return instance;
    }

    public List<Car> findAll() throws ServiceException{
        ArrayList<Car> cars = null;
        try{
            cars = new ArrayList<>(dao.findAll());
        } catch (DaoException ex){
            throw new ServiceException(ex);
        }
        return cars;
    }
}
