package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.Invoice;
import com.vgates.customerportal.util.MethodResult;

import java.util.Date;
import java.util.List;

/**
 * @author Chamith
 */
public interface MasterInvoiceDAO {

    MethodResult addNewInvoice(Invoice invoice);

    MethodResult updateInvoice(Invoice invoice);

    MethodResult changeInvoiceStatus(boolean status, long invoiceID);

    Invoice findInvoiceByID(long id);

    Invoice findInvoiceByInvoiceNo(String invNo);

    List<Invoice> findAllActiveInvoices();

    List<Invoice> findAllActiveInvoicesForDay(Date date);

    List<Invoice> findAllActiveInvoicesByMonth(int year, int month);

    List<Invoice> findAllActiveInvoicesByYear(int year);
}
