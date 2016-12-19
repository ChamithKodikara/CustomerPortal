package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.Payment;

/**
 * @author Chamith Kodikara
 */
public interface PaymentDAO {

    void addNewPayment(Payment payment);

    void updatePayment(Payment payment);

    void changePaymentStatus(boolean status, long paymentID);

    Payment findPaymentByID(long id);
}
