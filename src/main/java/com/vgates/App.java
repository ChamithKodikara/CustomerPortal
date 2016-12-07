package com.vgates;

import com.vgates.customerportal.view.user.UserLogin;
import org.apache.log4j.Logger;

;

/**
 * Hello world!
 */
public class App {
    final static Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) {
        UserLogin login = new UserLogin();
        login.setVisible(true);
//        System.out.println("Hello World!");
//
//        CustomerDetail customerDetail = new CustomerDetail();
//        customerDetail.setCustomerName("Chamith Sithara Kodikara");
//        customerDetail.setCustomerNo("000001");
//        CustomerDetailController customerDetailController = new CustomerDetailController();
//        customerDetailController.addNewCustomerDetail(customerDetail);


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
//     }

    }
}
