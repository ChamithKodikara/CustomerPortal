package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.MasterInvoiceDAO;
import com.vgates.customerportal.model.Invoice;
import com.vgates.customerportal.model.InvoiceServiceMapper;
import com.vgates.customerportal.model.MasterService;
import com.vgates.customerportal.session.HibernateSessionManager;
import com.vgates.customerportal.util.MethodResult;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * @author Chamith
 */
public class MasterInvoiceDAOImpl implements MasterInvoiceDAO {
    private final static Logger LOGGER = Logger.getLogger(MasterInvoiceDAOImpl.class);

    private final Session session;

    public MasterInvoiceDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    @Override
    public MethodResult addNewInvoice(Invoice invoice, List<MasterService> serviceList) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(invoice);
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Invoice Successfully Created !");

            createInvoiceServiceMap(invoice, serviceList);

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Invoice Error !");
        }
        return result;
    }

    @Override
    public MethodResult updateInvoice(Invoice invoice) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.update(invoice);
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Invoice Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setMessage("Sorry Invoice Update Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    @Override
    public MethodResult changeInvoiceStatus(boolean status, long invoiceID) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Invoice inv SET inv.active = :status WHERE inv.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", invoiceID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Invoice Status Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setMessage("Sorry Invoice Status Change Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    @Override
    public Invoice findInvoiceByID(long id) {
        Invoice invoice = null;
        try {
            Query query = session.createQuery("SELECT inv FROM Invoice inv WHERE inv.id= :id");
            query.setParameter("id", id);
            List<Invoice> resultList = (List<Invoice>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                invoice = resultList.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return invoice;
    }

    @Override
    public Invoice findInvoiceByInvoiceNo(String invNo) {
        Invoice invoice = null;
        try {
            Query query = session.createQuery("SELECT inv FROM Invoice inv WHERE inv.invoiceNo= :invNo");
            query.setParameter("invNo", invNo);
            List<Invoice> resultList = (List<Invoice>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                invoice = resultList.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return invoice;
    }

    @Override
    public List<Invoice> findAllActiveInvoices() {
        List<Invoice> resultList = null;
        try {
            Query query = session.createQuery("SELECT inv FROM Invoice inv WHERE inv.active= true ");
            resultList = (List<Invoice>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return resultList;
    }

    @Override
    public List<Invoice> findAllActiveInvoicesForDay(Date date) {
        List<Invoice> resultList = null;
        try {
            Query query = session.createQuery("SELECT inv FROM Invoice inv WHERE inv.active= true AND DATE(inv.createdDate) = :invDate");
            query.setParameter("invDate", date);
            resultList = (List<Invoice>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return resultList;
    }

    @Override
    public List<Invoice> findAllActiveInvoicesByMonth(int year, int month) {
        List<Invoice> resultList = null;
        try {
            Query query = session.createQuery("SELECT inv FROM Invoice inv WHERE inv.active= true AND YEAR(inv.createdDate) = :invYear AND MONTH(inv.createdDate) = :invMonth");
            query.setParameter("invYear", year);
            query.setParameter("invMonth", month);
            resultList = (List<Invoice>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return resultList;
    }

    @Override
    public List<Invoice> findAllActiveInvoicesByYear(int year) {
        List<Invoice> resultList = null;
        try {
            Query query = session.createQuery("SELECT inv FROM Invoice inv WHERE inv.active= true AND YEAR(inv.createdDate) = :invYear");
            query.setParameter("invYear", year);
            resultList = (List<Invoice>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return resultList;
    }

    @Override
    public List<InvoiceServiceMapper> findInvoiceServiceDetails(long invoiceId) {
        List<InvoiceServiceMapper> resultList = null;
        try {
            Query query = session.createQuery("SELECT map FROM InvoiceServiceMapper map WHERE map.invoice.id = :invId");
            query.setParameter("invId", invoiceId);
            resultList = (List<InvoiceServiceMapper>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return resultList;
    }

    private void createInvoiceServiceMap(Invoice invoice, List<MasterService> serviceList) {
        try {
            for (MasterService service : serviceList) {
                InvoiceServiceMapper map = new InvoiceServiceMapper();
                map.setInvoice(invoice);
                map.setMasterService(service);
                map.setMasterServiceCost(service.getCost());
                session.beginTransaction();
                session.save(map);
                session.getTransaction().commit();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
