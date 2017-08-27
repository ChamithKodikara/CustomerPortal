package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.EmployeeDetailDAO;
import com.vgates.customerportal.dao.impl.EmployeeDetailDAOImpl;
import com.vgates.customerportal.model.EmployeeDetail;
import com.vgates.customerportal.util.MethodResult;

import java.util.List;

public class EmployeeDetailController {
    private EmployeeDetailDAO employeeDetailDAO;

    public EmployeeDetailController() {
        employeeDetailDAO = new EmployeeDetailDAOImpl();
    }

    public MethodResult addNewEmployeeDetail(EmployeeDetail employeeDetail) {
        return employeeDetailDAO.addNewEmployeeDetail(employeeDetail);
    }

    public MethodResult updateEmployeeDetail(EmployeeDetail employeeDetail) {
        return employeeDetailDAO.updateEmployeeDetail(employeeDetail);
    }

    public MethodResult changeEmployeeStatus(boolean status, long employeeID) {
        return employeeDetailDAO.changeEmployeeStatus(status, employeeID);
    }

    public EmployeeDetail searchEmployeeByID(long employeeID) {
        return employeeDetailDAO.findEmployeeDetailByID(employeeID);
    }

    public List<EmployeeDetail> searchCustmerByName(String employeeName) {
        return employeeDetailDAO.findEmployeeDetailByName(employeeName);
    }

    public List<EmployeeDetail> getAllActiveEmployeeList() {
        return employeeDetailDAO.findAllActiveEmployeeDetails();
    }

}
