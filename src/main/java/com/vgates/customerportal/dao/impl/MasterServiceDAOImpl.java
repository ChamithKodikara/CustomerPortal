package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.MasterServiceDAO;
import com.vgates.customerportal.model.MasterService;
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
public class MasterServiceDAOImpl implements MasterServiceDAO {
    private final static Logger LOGGER = Logger.getLogger(MasterServiceDAOImpl.class);

    private final Session session;

    public MasterServiceDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    public MethodResult addNewMasterService(MasterService service) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
            session.close();
            result.setOk(true);
            result.setMessage("Service Details Successfully Added !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Service Detail Add Error !", ex);
            result.setMessage("Sorry Service Detail Add Error !");
            result.setStackMessage(ex.getMessage());

        }
        return result;
    }

    public MethodResult updateMasterService(MasterService service) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.update(service);
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Service Details Successfully updated !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Service Detail Update Error !", ex);
            result.setMessage("Sorry Service Detail Update Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    public MethodResult changeServiceStatus(boolean status, long serviceID) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE MasterService service SET service.active = :status WHERE service.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", serviceID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Service Status Successfully updated !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Service Status Change Error !", ex);
            result.setMessage("Sorry Service Status Change Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
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
