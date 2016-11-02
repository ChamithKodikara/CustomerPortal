package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.CustomerDetail;

/**
 * Created by Chamith on 11/1/2016.
 */
public interface CustomerDetailDAO {

    void addNewCustomerDetail(CustomerDetail customerDetail);

    void updateCustomerDetail(CustomerDetail customerDetail);

    CustomerDetail findCustomerDetailByID(long id);

}
