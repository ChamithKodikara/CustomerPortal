package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.EmployeeDetail;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

/**
 * @author Chamith
 */
public interface EmployeeDetailDAO {

    MethodResult addNewEmployeeDetail(EmployeeDetail customerDetail);

    MethodResult updateEmployeeDetail(EmployeeDetail customerDetail);

    MethodResult changeEmployeeStatus(boolean status, long customerID);

    EmployeeDetail findEmployeeDetailByID(long id);

    List<EmployeeDetail> findEmployeeDetailByName(String name);

    List<EmployeeDetail> findAllActiveEmployeeDetails();
}
