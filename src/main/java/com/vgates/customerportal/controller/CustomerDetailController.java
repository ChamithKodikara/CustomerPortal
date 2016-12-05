package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.CustomerDetailDAO;
import com.vgates.customerportal.dao.impl.CustomerDetailDAOImpl;
import com.vgates.customerportal.model.CustomerDetail;

import java.util.List;

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

    public void updateCustomerDetail(CustomerDetail customerDetail) {
        customerDetailDAO.updateCustomerDetail(customerDetail);
    }

    public void changeCustomerStatus(boolean status, long customerID) {
        customerDetailDAO.changeCustomerStatus(status, customerID);
    }

    public CustomerDetail searchCustomerByID(long customerID) {
        return customerDetailDAO.findCustomerDetailByID(customerID);
    }

    public List<CustomerDetail> searchCustmerByName(String customerName) {
        return customerDetailDAO.findCustomerDetailByName(customerName);
    }

    public CustomerDetail searchCustomerByCustomerNo(String customerNo) {
        return customerDetailDAO.findCustomerDetailByCustomerNo(customerNo);
    }

    public List<CustomerDetail> getAllActiveCustomerList() {
        return customerDetailDAO.findAllActiveCustomerDetails();
    }

    public List<CustomerDetail> getAllCustomers() {
        return customerDetailDAO.findAllCustomerDetails();
    }
}
