package by.bsuir.spp.autoservice.service.util;

import by.bsuir.spp.autoservice.dao.BaseDao;
import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.entity.Entity;
import by.bsuir.spp.autoservice.service.ServiceException;

public class ServiceUtil {
    private ServiceUtil() {}

    public static boolean save(Entity entity, BaseDao dao) throws ServiceException {
        boolean result = false;
        if (entity != null) {
            try {
                if (dao.save(entity) != null) {
                    result = true;
                }
            } catch (DaoException ex) {
                throw new ServiceException(ex);
            }
        }

        return result;
    }
}
