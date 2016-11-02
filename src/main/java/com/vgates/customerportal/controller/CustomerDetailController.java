package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.CustomerDetailDAO;
import com.vgates.customerportal.dao.impl.CustomerDetailDAOImpl;
import com.vgates.customerportal.model.CustomerDetail;

/**
 * Created by Chamith on 11/1/2016.
 */
public class CustomerDetailController {
    private CustomerDetailDAO customerDetailDAO;

    public CustomerDetailController() {
        customerDetailDAO = new CustomerDetailDAOImpl();
    }

    public void addNewCustomerDetail(CustomerDetail customerDetail) {
        customerDetailDAO.addNewCustomerDetail(customerDetail);
    }
}
