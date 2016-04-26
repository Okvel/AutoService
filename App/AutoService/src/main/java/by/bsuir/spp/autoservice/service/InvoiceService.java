package by.bsuir.spp.autoservice.service;

import by.bsuir.spp.autoservice.dao.DaoException;
import by.bsuir.spp.autoservice.dao.InvoiceDao;
import by.bsuir.spp.autoservice.entity.Invoice;
import by.bsuir.spp.autoservice.service.util.ServiceUtil;

public class InvoiceService extends BaseService {
    private static InvoiceService instance = new InvoiceService();
    private static InvoiceDao dao = factory.getInvoiceDao();

    private InvoiceService() {}

    public static InvoiceService getInstance() {
        return instance;
    }

    public boolean save(Invoice invoice) throws ServiceException {
        return ServiceUtil.save(invoice, dao);
    }
}
