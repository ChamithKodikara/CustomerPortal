package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.CustomerDetailDAO;
import com.vgates.customerportal.model.CustomerDetail;
import com.vgates.customerportal.session.HibernateSessionManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Created by Chamith on 11/1/2016.
 */
public class CustomerDetailDAOImpl implements CustomerDetailDAO {
    private final static Logger LOGGER = Logger.getLogger(CustomerDetailDAOImpl.class);

    private final Session session;

    public CustomerDetailDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    public void addNewCustomerDetails(CustomerDetail customerDetail) {
        try {
            session.beginTransaction();
            session.save(customerDetail);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("Sorry Customer Detail Add Error !", ex);
        }

    }
}
