package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.MasterItemDAO;
import com.vgates.customerportal.model.MasterItem;
import com.vgates.customerportal.session.HibernateSessionManager;
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

    public void addNewMasterItem(MasterItem item) {
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("Sorry Item Detail Add Error !", ex);
        }

    }

    public void updateMasterItem(MasterItem item) {
        try {
            session.beginTransaction();
            session.update(item);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry Item Detail Update Error !", ex);
        }
    }

    public void changeItemStatus(boolean status, long itemID) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE MasterItem item SET item.active = :status WHERE item.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", itemID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry Item Status Change Error !", ex);
        }
    }

    public MasterItem findMasterItemByID(long id) {
        MasterItem item = null;
        try {
            Query query = session.createQuery("SELECT item FROM MasterItem item WHERE item.id= :id");
            query.setParameter("id", id);
            List<MasterItem> resultList = (List<MasterItem>) query.list();
            item = resultList.get(0);
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
            item = resultList.get(0);
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
