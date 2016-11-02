package com.vgates;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

;

/**
 * Hello world!
 */
public class App {
    final static Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) {
        System.out.println("Hello World!");

//        CustomerDetail customerDetail = new CustomerDetail();
//        customerDetail.setCustomerName("Chamith Sithara Kodikara");
//        CustomerDetailController customerDetailController = new CustomerDetailController();
//        customerDetailController.addNewCustomerDetails(customerDetail);


        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty("database", "vg_customerportal");
            prop.setProperty("dbuser", "root");
            prop.setProperty("dbpassword", "admin");

            // save properties to project root folder
            prop.store(output, null);
            LOGGER.info("SUCCESS");

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
