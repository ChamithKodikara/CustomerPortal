package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.MasterInvoiceDAO;
import com.vgates.customerportal.model.Invoice;
import com.vgates.customerportal.session.HibernateSessionManager;
import com.vgates.customerportal.util.MethodResult;
import org.apache.log4j.Logger;
import org.hibernate.Session;

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
    public MethodResult addNewInvoice(Invoice invoice) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(invoice);
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Invoice Successfully Created !");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Invoice Error !");
        }
        return result;
    }

    @Override
    public MethodResult updateInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public MethodResult changeInvoiceStatus(boolean status, long invoiceID) {
        return null;
    }

    @Override
    public Invoice findInvoiceByID(long id) {
        return null;
    }

    @Override
    public Invoice findInvoiceByInvoiceNo(String name) {
        return null;
    }

    @Override
    public List<Invoice> findAllActiveInvoices() {
        return null;
    }
}
