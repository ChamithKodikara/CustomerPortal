package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.PaymentDAO;
import com.vgates.customerportal.dao.impl.PaymentDAOImpl;
import com.vgates.customerportal.model.Payment;

/**
 * @author Chamith
 */
public class PaymentController {
    private PaymentDAO paymentDAO;

    public PaymentController() {
        paymentDAO = new PaymentDAOImpl();
    }

    public void addNewPayment(Payment payment) {
        paymentDAO.addNewPayment(payment);
    }

    public void updatePayment(Payment payment) {
        paymentDAO.updatePayment(payment);
    }

    public void updatePaymentStatus(boolean status, long paymentID) {
        paymentDAO.changePaymentStatus(status, paymentID);
    }

    public Payment findPaymentByID(long paymentID) {
        return paymentDAO.findPaymentByID(paymentID);
    }
}
