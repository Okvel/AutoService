package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RepairReportDao;
import by.bsuir.spp.autoservice.entity.RepairReport;

import java.util.ArrayList;
import java.util.List;

public class RepairReportService extends BaseService {
    private static RepairReportService instance = new RepairReportService();

    private RepairReportService() {}

    public static RepairReportService getInstance() {
        return instance;
    }

    public List<RepairReport> findAll() throws ServiceException {
        ArrayList<RepairReport> reports;
        try {
            RepairReportDao dao = factory.getRepairReportDao();
            reports = new ArrayList<>(dao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return reports;
    }
}
