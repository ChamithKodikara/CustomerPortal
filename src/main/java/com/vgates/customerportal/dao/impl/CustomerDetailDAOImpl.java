package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.CustomerDetailDAO;
import com.vgates.customerportal.model.CustomerDetail;
import com.vgates.customerportal.session.HibernateSessionManager;
import com.vgates.customerportal.util.MethodResult;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Chamith
 */
public class CustomerDetailDAOImpl implements CustomerDetailDAO {
    private final static Logger LOGGER = Logger.getLogger(CustomerDetailDAOImpl.class);

    private final Session session;

    public CustomerDetailDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    public MethodResult addNewCustomerDetail(CustomerDetail customerDetail) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(customerDetail);
            session.getTransaction().commit();
            session.close();
            result.setOk(true);
            result.setMessage("Customer Detail Successfully Added !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Detail Add Error !", ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Customer Detail Add Error !");
        }
        return result;
    }

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

    public CustomerDetail findCustomerDetailByID(long id) {
        CustomerDetail customerDetail = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.id= :id AND customer.active = true");
            query.setParameter("id", id);
            List<CustomerDetail> resultList = (List<CustomerDetail>) query.list();
            customerDetail = resultList.get(0);
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Customer Detail By ID !", ex);
        }
        return customerDetail;
    }

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

    public CustomerDetail findCustomerDetailByCustomerNo(String customerNo) {
        CustomerDetail customerDetail = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.customerNo= :customerNo AND customer.active = true");
            query.setParameter("customerNo", customerNo);
            List<CustomerDetail> resultList = (List<CustomerDetail>) query.list();
            customerDetail = resultList.get(0);
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Customer Detail By Customer No !", ex);
        }
        return customerDetail;
    }

    public List<CustomerDetail> findAllActiveCustomerDetails() {
        List<CustomerDetail> customerDetailList = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.active = true");
            customerDetailList = (List<CustomerDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find All Active Customer Details !", ex);
        }
        return customerDetailList;
    }

    public List<CustomerDetail> findAllCustomerDetails() {
        List<CustomerDetail> customerDetailList = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer");
            customerDetailList = (List<CustomerDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find All Active Customer Details !", ex);
        }
        return customerDetailList;
    }
}
