package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.CustomerDetailDAO;
import com.vgates.customerportal.model.CustomerDetail;
import com.vgates.customerportal.session.HibernateSessionManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Chamith on 11/1/2016.
 */
public class CustomerDetailDAOImpl implements CustomerDetailDAO {
    private final static Logger LOGGER = Logger.getLogger(CustomerDetailDAOImpl.class);

    private final Session session;

    public CustomerDetailDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    public void addNewCustomerDetail(CustomerDetail customerDetail) {
        try {
            session.beginTransaction();
            session.save(customerDetail);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Detail Add Error !", ex);
        }

    }

    public void updateCustomerDetail(CustomerDetail customerDetail) {
        try {
            session.beginTransaction();
            session.update(customerDetail);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Detail Update Error !", ex);
        }
    }

    public void changeCustomerStatus(boolean status, long customerID) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE CustomerDetail customer SET customer.active = :status WHERE customer.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", customerID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Status Change Error !", ex);
        }
    }

    public CustomerDetail findCustomerDetailByID(long id) {
        CustomerDetail customerDetail = null;
        try {
            Query query = session.createQuery("SELECT customer FROM CustomerDetail customer WHERE customer.id= :id");
            query.setParameter("id", id);
            List<CustomerDetail> resultList = (List<CustomerDetail>) query.list();
            customerDetail = resultList.get(0);
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Customer Detail !", ex);
        }
        return customerDetail;
    }
}
