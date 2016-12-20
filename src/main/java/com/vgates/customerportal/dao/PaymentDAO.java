package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.Payment;
import com.vgates.customerportal.util.MethodResult;

/**
 * @author Chamith Kodikara
 */
public interface PaymentDAO {

    MethodResult addNewPayment(Payment payment);

    MethodResult updatePayment(Payment payment);

    MethodResult changePaymentStatus(boolean status, long paymentID);

    Payment findPaymentByID(long id);
}
