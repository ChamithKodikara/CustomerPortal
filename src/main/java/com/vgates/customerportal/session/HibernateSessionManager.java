package com.vgates.customerportal.session;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

/**
 * @author Chamith
 */


public class HibernateSessionManager {

    final static Logger LOGGER = Logger.getLogger(HibernateSessionManager.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();
    public static Connection getConnection() throws SQLException{
       return ((SessionFactoryImpl) sessionFactory).getConnectionProvider().getConnection();
    }
    private static SessionFactory buildSessionFactory() {
        try {

            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            LOGGER.error("SessionFactory creation failed. !" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}

