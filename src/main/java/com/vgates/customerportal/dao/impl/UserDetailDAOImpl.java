package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.UserDetailDAO;
import com.vgates.customerportal.model.UserDetail;
import com.vgates.customerportal.session.HibernateSessionManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Chamith on 11/21/2016.
 */
public class UserDetailDAOImpl implements UserDetailDAO {
    private final static Logger LOGGER = Logger.getLogger(UserDetailDAOImpl.class);

    private final Session session;

    public UserDetailDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    public void addNewUserDetail(UserDetail userDetail) {
        try {
            session.beginTransaction();
            session.save(userDetail);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ex) {
            LOGGER.error("Sorry User Detail Add Error !", ex);
        }

    }

    public void updateUserDetail(UserDetail userDetail) {
        try {
            session.beginTransaction();
            session.update(userDetail);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry User Detail Update Error !", ex);
        }
    }

    public void changeUserStatus(boolean status, long userID) {
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE UserDetail user SET user.active = :status WHERE user.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", userID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.error("Sorry User Status Change Error !", ex);
        }
    }

    public UserDetail findUserDetailByID(long id) {
        UserDetail userDetail = null;
        try {
            Query query = session.createQuery("SELECT user FROM UserDetail user WHERE user.id= :id");
            query.setParameter("id", id);
            List<UserDetail> resultList = (List<UserDetail>) query.list();
            userDetail = resultList.get(0);
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find User Detail !", ex);
        }
        return userDetail;
    }

    public UserDetail findUserDetailForActiveLogin() {
        UserDetail userDetail = null;
        try {
            Query query = session.createQuery("SELECT user FROM UserDetail user WHERE user.activeUser= true");
            List<UserDetail> resultList = (List<UserDetail>) query.list();
            userDetail = resultList.get(0);
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find User Detail !", ex);
        }
        return userDetail;
    }

    public boolean loginUser(String userName, String password) {
        boolean loginSuccess = false;
        try {
            Query query = session.createQuery("SELECT user FROM UserDetail user WHERE user.userName= :userName AND user.password= :password AND user.active=true ");
            query.setParameter("userName", userName);
            query.setParameter("password", password);
            List<UserDetail> resultList = (List<UserDetail>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                UserDetail userDetail = resultList.get(0);
                userDetail.setActiveUser(Boolean.TRUE);
                loginSuccess = Boolean.TRUE;
                updateUserDetail(userDetail);
            }
        } catch (NoResultException ex) {
            LOGGER.error("Sorry Cannot Find User Detail To Login !", ex);
        }
        return loginSuccess;
    }

    public boolean logoutUser(String userName) {
        boolean logoutSuccess = false;
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE UserDetail user SET user.activeUser=false,user.lastActDate =current_timestamp WHERE user.userName= :userName ");
            query.setParameter("userName", userName);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            logoutSuccess = Boolean.TRUE;
        } catch (NoResultException ex) {
            LOGGER.error("Logout Failed !", ex);
        }
        return logoutSuccess;

    }

    public boolean logoutAllUsers() {
        boolean logoutSuccess = false;
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE UserDetail user SET user.activeUser=false, user.lastActDate =CURRENT_TIMESTAMP ");
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            logoutSuccess = Boolean.TRUE;
        } catch (NoResultException ex) {
            LOGGER.error("Logout Failed !", ex);
        }
        return logoutSuccess;

    }
}
