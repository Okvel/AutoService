package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.RepairReportDao;
import by.bsuir.spp.autoservice.entity.RepairReport;
import by.bsuir.spp.autoservice.service.util.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class RepairReportService extends BaseService {
    private static RepairReportService instance = new RepairReportService();
    private static RepairReportDao dao = factory.getRepairReportDao();

    private RepairReportService() {}

    public static RepairReportService getInstance() {
        return instance;
    }

    public List<RepairReport> findAll() throws ServiceException {
        ArrayList<RepairReport> reports;
        try {
            reports = new ArrayList<>(dao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }

        return reports;
    }

    public RepairReport findById(Long id) throws ServiceException {
        RepairReport repairReport;
        try{
            repairReport = dao.findById(id);
        } catch (DaoException ex){
            throw new ServiceException(ex);
        }
        return repairReport;
    }

    public boolean save(RepairReport report) throws ServiceException {
        return ServiceUtil.save(report, dao);
    }
}
