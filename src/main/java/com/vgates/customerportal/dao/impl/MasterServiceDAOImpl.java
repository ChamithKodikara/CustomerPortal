package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.MasterServiceDAO;
import com.vgates.customerportal.model.MasterService;
import com.vgates.customerportal.session.HibernateSessionManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Chamith on 11/21/2016.
 */
public class MasterServiceDAOImpl implements MasterServiceDAO {
    private final static Logger LOGGER = Logger.getLogger(MasterServiceDAOImpl.class);

    private final Session session;

    public MasterServiceDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    public void addNewMasterService(MasterService service) {
        try {
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("Sorry Service Detail Add Error !", ex);
        }

    }

    public void updateMasterService(MasterService service) {
        try {
            session.beginTransaction();
            session.update(service);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry Service Detail Update Error !", ex);
        }
    }

    public void changeServiceStatus(boolean status, long serviceID) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE MasterService service SET service.active = :status WHERE service.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", serviceID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry Service Status Change Error !", ex);
        }
    }

    public MasterService findMasterServiceByID(long id) {
        MasterService service = null;
        try {
            Query query = session.createQuery("SELECT service FROM MasterService service WHERE service.id= :id");
            query.setParameter("id", id);
            List<MasterService> resultList = (List<MasterService>) query.list();
            service = resultList.get(0);
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Service Detail !", ex);
        }
        return service;
    }

    public List<MasterService> findMasterServiceByName(String name) {
        List<MasterService> serviceList = null;
        try {
            Query query = session.createQuery("SELECT service FROM MasterService service WHERE service.serviceName LIKE :name");
            query.setParameter("name", name);
            List<MasterService> resultList = (List<MasterService>) query.list();
        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find Service Detail !", ex);
        }
        return serviceList;
    }
}
