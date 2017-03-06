package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.CustomerDetailDAO;
import com.vgates.customerportal.model.CustomerDetail;
import com.vgates.customerportal.session.HibernateSessionManager;
import com.vgates.customerportal.util.MethodResult;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @author Chamith
 */
public class CustomerDetailDAOImpl implements CustomerDetailDAO {
    private final static Logger LOGGER = Logger.getLogger(CustomerDetailDAOImpl.class);

    private final Session session;

   private final DecimalFormat decimalFormat = new DecimalFormat("###");

    public CustomerDetailDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    @Override
    public String newCustomerRefNo() {
        StringBuilder queryBulider=new StringBuilder();
        queryBulider.append("SELECT FLOOR(RAND() * 999999)AS custRef FROM CUSTOMER_DETAIL WHERE 'custRef' NOT IN");
        queryBulider.append("(SELECT CUSTOMER_NO FROM CUSTOMER_DETAIL) LIMIT 1");
        Query query = session.createSQLQuery(queryBulider.toString());
        List list = query.list();
        return decimalFormat.format(list.get(0));
    }

    @Override
    public MethodResult addNewCustomerDetail(CustomerDetail customerDetail) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(customerDetail);
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Customer Detail Successfully Added !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Detail Add Error !", ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Customer Detail Add Error !");
        }
        return result;
    }

    @Override
    public MethodResult updateCustomerDetail(CustomerDetail customerDetail) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.update(customerDetail);
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Customer Detail Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Detail Update Error !", ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Customer Detail Update Error !");
        }
        return result;
    }

    @Override
    public MethodResult changeCustomerStatus(boolean status, long customerID) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE CustomerDetail customer SET customer.active = :status WHERE customer.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", customerID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Customer Status Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Status Change Error !", ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Customer Status Change Error !");
        }
        return result;
    }

    @Override
    public CustomerDetail findCustomerDetailByID(long id) {
        CustomerDetail customerDetail = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.id= :id AND customer.active = true");
            query.setParameter("id", id);
            List<CustomerDetail> resultList = (List<CustomerDetail>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                customerDetail = resultList.get(0);
            }
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Customer Detail By ID !", ex);
        }
        return customerDetail;
    }

    @Override
    public List<CustomerDetail> findCustomerDetailByName(String name) {
        List<CustomerDetail> customerDetailList = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.customerName= :name AND customer.active = true");
            query.setParameter("name", name);
            customerDetailList = (List<CustomerDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find Customer Detail By Name !", ex);
        }
        return customerDetailList;
    }

    @Override
    public CustomerDetail findCustomerDetailByCustomerNo(String customerNo) {
        CustomerDetail customerDetail = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.customerNo= :customerNo AND customer.active = true");
            query.setParameter("customerNo", customerNo);
            List<CustomerDetail> resultList = (List<CustomerDetail>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                customerDetail = resultList.get(0);
            }
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Customer Detail By Customer No !", ex);
        }
        return customerDetail;
    }

    @Override
    public List<CustomerDetail> findAllActiveCustomerDetails() {
        List<CustomerDetail> customerDetailList = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.active = true ORDER BY customer.customerName ASC ");
            customerDetailList = (List<CustomerDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find All Active Customer Details !", ex);
        }
        return customerDetailList;
    }

    @Override
    public List<CustomerDetail> findActiveCustomerDetails(String customerName, String refNo, String nic) {
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbRef = new StringBuilder();
        StringBuilder sbNIC = new StringBuilder();
        if (customerName == null || customerName.isEmpty()) {
            sbName.append("%%");
        } else {
            sbName.append("%").append(customerName).append("%");
        }
        if (refNo == null || refNo.isEmpty()) {
            sbRef.append("%%");
        } else {
            sbRef.append("%").append(refNo).append("%");
        }
        if (nic == null || nic.isEmpty()) {
            sbNIC.append("%%");
        } else {
            sbNIC.append("%").append(nic).append("%");
        }
        List<CustomerDetail> customerDetailList = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.active = true AND customer.customerName LIKE :name AND customer.customerNo LIKE :ref AND customer.nic LIKE :nic ORDER BY customer.customerName ASC ");
            query.setParameter("name", sbName.toString());
            query.setParameter("ref", sbRef.toString());
            query.setParameter("nic", sbNIC.toString());
            customerDetailList = (List<CustomerDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find All Active Customer Details !", ex);
        }
        return customerDetailList;
    }

    @Override
    public List<CustomerDetail> findAllCustomerDetails() {
        List<CustomerDetail> customerDetailList = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer ORDER BY customer.customerName ASC ");
            customerDetailList = (List<CustomerDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find All Active Customer Details !", ex);
        }
        return customerDetailList;
    }
}
