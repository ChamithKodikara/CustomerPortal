package com.vgates;

import org.apache.log4j.Logger;

;

/**
 * Hello world!
 */
public class App {
    final static Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");

        App obj = new App();
//        obj.runMe("test log 4j");

//        SessionFactory sessionFactory = HibernateSessionManager.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        CustomerDetail customerDetail =new CustomerDetail();
//        customerDetail.setCustomerName("Manoj");
//
//        session.beginTransaction();
//        session.save(customerDetail);
//        session.getTransaction().commit();
//        session.close();
//        HibernateSessionManager.shutdown();

//        Properties prop = new Properties();
//        OutputStream output = null;
//
//        try {
//
//            output = new FileOutputStream("config.properties");
//
//            // set the properties value
//            prop.setProperty("database", "vg_customerportal");
//            prop.setProperty("dbuser", "root");
//            prop.setProperty("dbpassword", "admin");
//
//            // save properties to project root folder
//            prop.store(output, null);
//            LOGGER.info("SUCCESS");
//
//        } catch (IOException io) {
//            io.printStackTrace();
//        } finally {
//            if (output != null) {
//                try {
//                    output.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
    }

    private void runMe(String parameter){

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("This is debug : " + parameter);
        }

        if(LOGGER.isInfoEnabled()){
            LOGGER.info("This is info : " + parameter);
        }

        LOGGER.warn("This is warn : " + parameter);
        LOGGER.error("This is error : " + parameter);
        LOGGER.fatal("This is fatal : " + parameter);

    }
}
