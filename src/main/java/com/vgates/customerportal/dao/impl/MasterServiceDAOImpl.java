package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.MasterServiceDAO;
import com.vgates.customerportal.model.MasterService;
import com.vgates.customerportal.session.HibernateSessionManager;
import com.vgates.customerportal.util.MethodResult;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

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

    @Override
    public MethodResult addNewMasterService(MasterService service) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Service Details Successfully Added !");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setMessage("Sorry Service Detail Add Error !");
            result.setStackMessage(ex.getMessage());

        }
        return result;
    }

    @Override
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
            LOGGER.error(ex.getMessage(), ex);
            result.setMessage("Sorry Service Detail Update Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    @Override
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
            LOGGER.error(ex.getMessage(), ex);
            result.setMessage("Sorry Service Status Change Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    @Override
    public MasterService findMasterServiceByID(long id) {
        MasterService service = null;
        try {
            Query query = session.createQuery("SELECT service FROM MasterService service WHERE service.id= :id AND service.active = true");
            query.setParameter("id", id);
            List<MasterService> resultList = (List<MasterService>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                service = resultList.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return service;
    }

    @Override
    public List<MasterService> findMasterServiceByName(String name) {
        List<MasterService> serviceList = null;
        try {
            Query query = session.createQuery("SELECT service FROM MasterService service WHERE service.active = true AND service.serviceName LIKE :name");
            query.setParameter("name", name);
            serviceList = (List<MasterService>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return serviceList;
    }

    @Override
    public List<MasterService> findAllActiveMasterService() {
        List<MasterService> serviceList = null;
        try {
            Query query = session.createQuery("SELECT service FROM MasterService service WHERE service.active = true ");
            serviceList = (List<MasterService>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return serviceList;
    }

    @Override
    public List<MasterService> findMasterService(String name, String category) {
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbCategory = new StringBuilder();
        if (name == null || name.isEmpty()) {
            sbName.append("%%");
        } else {
            sbName.append("%").append(name).append("%");
        }
        if (category == null || category.isEmpty()) {
            sbCategory.append("%%");
        } else {
            sbCategory.append("%").append(category).append("%");
        }

        List<MasterService> serviceList = null;
        try {
            Query query = session.createQuery("SELECT service FROM MasterService service WHERE service.active = true AND service.serviceName LIKE :name AND service.category LIKE :category");
            query.setParameter("name", sbName.toString());
            query.setParameter("category", sbCategory.toString());
            serviceList = (List<MasterService>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return serviceList;
    }

    @Override
    public List<MasterService> searchAllActiveServiceByCategory(String category) {
        List<MasterService> serviceList = null;
        try {
            Query query = session.createQuery("SELECT service FROM MasterService service WHERE service.active = true AND service.category =:category");
            query.setParameter("category", category);
            serviceList = (List<MasterService>) query.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return serviceList;
    }
}
