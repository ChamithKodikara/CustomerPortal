package com.vgates.customerportal.dao;

import com.vgates.customerportal.model.Invoice;
import com.vgates.customerportal.model.InvoiceServiceMapper;
import com.vgates.customerportal.model.MasterService;
import com.vgates.customerportal.util.MethodResult;

import java.util.Date;
import java.util.List;

/**
 * @author Chamith
 */
public interface MasterInvoiceDAO {

    String newInvoiceNo();

    MethodResult addNewInvoice(Invoice invoice, List<MasterService> serviceList);

    MethodResult updateInvoice(Invoice invoice);

    MethodResult changeInvoiceStatus(boolean status, long invoiceID);

    Invoice findInvoiceByID(long id);

    Invoice findInvoiceByInvoiceNo(String invNo);

    List<Invoice> findAllActiveInvoices();

    List<Invoice> findAllActiveInvoicesForDay(Date date, String category);

    List<Invoice> findAllActiveInvoicesByMonth(int year, int month, String category);

    List<Invoice> findAllActiveInvoicesByYear(int year, String category);

    List<InvoiceServiceMapper> findInvoiceServiceDetails(long invoiceId);


}
