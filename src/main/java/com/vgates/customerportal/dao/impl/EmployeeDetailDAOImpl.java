package com.vgates.customerportal.dao.impl;

import com.vgates.customerportal.dao.EmployeeDetailDAO;
import com.vgates.customerportal.model.EmployeeDetail;
import com.vgates.customerportal.session.HibernateSessionManager;
import com.vgates.customerportal.util.MethodResult;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Chamith
 */
public class EmployeeDetailDAOImpl implements EmployeeDetailDAO {

    private final static Logger LOGGER = Logger.getLogger(EmployeeDetailDAOImpl.class);

    private final Session session;

    public EmployeeDetailDAOImpl() {
        session = HibernateSessionManager.getSessionFactory().openSession();
    }

    @Override
    public MethodResult addNewEmployeeDetail(EmployeeDetail employeeDetail) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.save(employeeDetail);
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Employee Detail Successfully Added !");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Employee Detail Add Error !");
        }
        return result;
    }

    @Override
    public MethodResult updateEmployeeDetail(EmployeeDetail employeeDetail) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            session.update(employeeDetail);
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Employee Detail Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Employee Detail Update Error !");
        }
        return result;
    }

    @Override
    public MethodResult changeEmployeeStatus(boolean status, long employeeID) {
        MethodResult result = new MethodResult();
        result.setOk(false);
        try {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE EmployeeDetail employee SET employee.active = :status WHERE employee.id= :id");
            query.setParameter("status", status);
            query.setParameter("id", employeeID);
            query.executeUpdate();
            session.flush();
            session.getTransaction().commit();
            result.setOk(true);
            result.setMessage("Employee Status Successfully Updated !");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            result.setStackMessage(ex.getMessage());
            result.setMessage("Sorry Employee Status Change Error !");
        }
        return result;
    }

    @Override
    public EmployeeDetail findEmployeeDetailByID(long id) {
        EmployeeDetail employeeDetail = null;
        try {
            Query query = session.createQuery("SELECT employee FROM EmployeeDetail employee WHERE employee.id= :id AND employee.active = true");
            query.setParameter("id", id);
            List<EmployeeDetail> resultList = (List<EmployeeDetail>) query.list();
            if (resultList != null && !resultList.isEmpty()) {
                employeeDetail = resultList.get(0);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return employeeDetail;
    }

    @Override
    public List<EmployeeDetail> findEmployeeDetailByName(String name) {
        List<EmployeeDetail> employeeDetailList = null;
        try {
            Query query = session.createQuery("SELECT employee FROM EmployeeDetail employee WHERE employee.employeeName= :name AND employee.active = true");
            query.setParameter("name", name);
            employeeDetailList = (List<EmployeeDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return employeeDetailList;
    }

    @Override
    public List<EmployeeDetail> findAllActiveEmployeeDetails() {
        List<EmployeeDetail> customerDetailList = null;
        try {
            Query query = session.createQuery("SELECT employee FROM EmployeeDetail employee WHERE employee.active = true ORDER BY employee.employeeName ASC ");
            customerDetailList = (List<EmployeeDetail>) query.list();

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return customerDetailList;
    }
}
