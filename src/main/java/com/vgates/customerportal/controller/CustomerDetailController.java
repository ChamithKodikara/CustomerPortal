package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.CustomerDetailDAO;
import com.vgates.customerportal.dao.impl.CustomerDetailDAOImpl;
import com.vgates.customerportal.model.CustomerDetail;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public class CustomerDetailController {
    private CustomerDetailDAO customerDetailDAO;

    public CustomerDetailController() {
        customerDetailDAO = new CustomerDetailDAOImpl();
    }

    public String newCustomerRef() {
        return customerDetailDAO.newCustomerRefNo();
    }

    public MethodResult addNewCustomerDetail(CustomerDetail customerDetail) {
        return customerDetailDAO.addNewCustomerDetail(customerDetail);
    }

    public MethodResult updateCustomerDetail(CustomerDetail customerDetail) {
        return customerDetailDAO.updateCustomerDetail(customerDetail);
    }

    public MethodResult changeCustomerStatus(boolean status, long customerID) {
        return customerDetailDAO.changeCustomerStatus(status, customerID);
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

    public List<CustomerDetail> getActiveCustomerList(String customerName, String refNo, String category) {
        return customerDetailDAO.findActiveCustomerDetails(customerName, refNo, category);
    }

    public List<CustomerDetail> getAllCustomers() {
        return customerDetailDAO.findAllCustomerDetails();
    }
}
