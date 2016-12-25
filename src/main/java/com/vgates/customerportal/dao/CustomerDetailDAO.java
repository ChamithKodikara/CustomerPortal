package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.CustomerDetail;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
public interface CustomerDetailDAO {

    MethodResult addNewCustomerDetail(CustomerDetail customerDetail);

    MethodResult updateCustomerDetail(CustomerDetail customerDetail);

    MethodResult changeCustomerStatus(boolean status, long customerID);

    CustomerDetail findCustomerDetailByID(long id);

    List<CustomerDetail> findCustomerDetailByName(String name);

    CustomerDetail findCustomerDetailByCustomerNo(String customerNo);

    List<CustomerDetail> findAllActiveCustomerDetails();

    List<CustomerDetail> findActiveCustomerDetails(String customerName, String refNo, String nic);

    List<CustomerDetail> findAllCustomerDetails();

}
