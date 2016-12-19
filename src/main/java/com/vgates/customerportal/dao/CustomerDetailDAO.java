package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.CustomerDetail;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public interface CustomerDetailDAO {

    void addNewCustomerDetail(CustomerDetail customerDetail);

    void updateCustomerDetail(CustomerDetail customerDetail);

    void changeCustomerStatus(boolean status, long customerID);

    CustomerDetail findCustomerDetailByID(long id);

    List<CustomerDetail> findCustomerDetailByName(String name);

    CustomerDetail findCustomerDetailByCustomerNo(String customerNo);

    List<CustomerDetail> findAllActiveCustomerDetails();

    List<CustomerDetail> findAllCustomerDetails();

}
