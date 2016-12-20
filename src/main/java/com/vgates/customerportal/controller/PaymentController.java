package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.PaymentDAO;
import com.vgates.customerportal.dao.impl.PaymentDAOImpl;
import com.vgates.customerportal.model.Payment;
import com.vgates.customerportal.util.MethodResult;

/**
 * @author Chamith
 */
public class PaymentController {
    private PaymentDAO paymentDAO;

    public PaymentController() {
        paymentDAO = new PaymentDAOImpl();
    }

    public MethodResult addNewPayment(Payment payment) {
        return paymentDAO.addNewPayment(payment);
    }

    public MethodResult updatePayment(Payment payment) {
        return paymentDAO.updatePayment(payment);
    }

    public MethodResult updatePaymentStatus(boolean status, long paymentID) {
        return paymentDAO.changePaymentStatus(status, paymentID);
    }

    public Payment findPaymentByID(long paymentID) {
        return paymentDAO.findPaymentByID(paymentID);
    }
}
