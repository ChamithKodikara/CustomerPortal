package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.MasterItemDAO;
import com.vgates.customerportal.model.MasterItem;
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
public class MasterItemDAOImpl implements MasterItemDAO {
    private final static Logger LOGGER = Logger.getLogger(MasterItemDAOImpl.class);

    private final Session session;

    public MasterItemDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    public MethodResult addNewMasterItem(MasterItem item) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Item Details Successfully Added !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Item Detail Add Error !", ex);
            result.setMessage("Sorry Item Detail Add Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    public MethodResult updateMasterItem(MasterItem item) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.update(item);
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Item Details Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Item Detail Update Error !", ex);
            result.setMessage("Sorry Item Detail Update Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    public MethodResult changeItemStatus(boolean status, long itemID) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE MasterItem item SET item.active = :status WHERE item.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", itemID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Item Status Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error("Sorry Item Status Change Error !", ex);
            result.setMessage("Sorry Item Status Change Error !");
            result.setStackMessage(ex.getMessage());
        }
        return result;
    }

    public MasterItem findMasterItemByID(long id) {
        MasterItem item = null;
        try {
            Query query = session.createQuery("SELECT item FROM MasterItem item WHERE item.id= :id");
            query.setParameter("id", id);
            List<MasterItem> resultList = (List<MasterItem>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                item = resultList.get(0);
            }
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Item Detail !", ex);
        }
        return item;
    }

    public MasterItem findMasterItemByName(String name) {
        MasterItem item = null;
        try {
            Query query = session.createQuery("SELECT item FROM MasterItem item WHERE item.itemName= :name");
            query.setParameter("name", name);
            List<MasterItem> resultList = (List<MasterItem>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                item = resultList.get(0);
            }
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find Item Detail !", ex);
        }
        return item;
    }

    public List<MasterItem> findAllActiveMasterItems() {
        List<MasterItem> resultList = null;
        try {
            Query query = session.createQuery("SELECT item FROM MasterItem item WHERE item.active= true ");
            resultList = (List<MasterItem>) query.list();
        } catch (Exception ex) {
            LOGGER.error("Sorry Cannot Find Item Detail !", ex);
        }
        return resultList;
    }
}
