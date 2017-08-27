package com.vgates.customerportal.controller;

import com.vgates.customerportal.dao.MasterInvoiceDAO;
import com.vgates.customerportal.dao.impl.MasterInvoiceDAOImpl;
import com.vgates.customerportal.model.Invoice;
import com.vgates.customerportal.model.InvoiceServiceMapper;
import com.vgates.customerportal.model.MasterService;
import com.vgates.customerportal.util.MethodResult;

import java.util.Date;
import java.util.List;

public class MasterInvoiceController {
    private MasterInvoiceDAO masterInvoiceDAO;

    public MasterInvoiceController() {
        masterInvoiceDAO = new MasterInvoiceDAOImpl();
    }

    public String newInvoiceNo() {
        return masterInvoiceDAO.newInvoiceNo();
    }
    public MethodResult generateNewInvoice(Invoice invoice, List<MasterService> serviceList) {
        return masterInvoiceDAO.addNewInvoice(invoice, serviceList);
    }

    public MethodResult updateInvoiceDetails(Invoice invoice) {
        return masterInvoiceDAO.updateInvoice(invoice);
    }

    public MethodResult updateInvoiceStatus(boolean status, long invoiceId) {
        return masterInvoiceDAO.changeInvoiceStatus(status, invoiceId);
    }

    public Invoice searchInvoiceDetailsById(long invoiceId) {
        return masterInvoiceDAO.findInvoiceByID(invoiceId);
    }

    public Invoice searchInvoiceDetailsById(String invoiceNo) {
        return masterInvoiceDAO.findInvoiceByInvoiceNo(invoiceNo);
    }

    public List<Invoice> getAllActiveInvoices() {
        return masterInvoiceDAO.findAllActiveInvoices();
    }

    public List<Invoice> getAllInvoiceOfDay(Date date) {
        return masterInvoiceDAO.findAllActiveInvoicesForDay(date);
    }

    public List<Invoice> getAllInvoicesOfMonth(int year, int month) {
        return masterInvoiceDAO.findAllActiveInvoicesByMonth(year, month);
    }

    public List<Invoice> getAllInvoicesOfYear(int year) {
        return masterInvoiceDAO.findAllActiveInvoicesByYear(year);
    }

    public List<InvoiceServiceMapper> getInvoiceServiceDetails(long invId) {
        return masterInvoiceDAO.findInvoiceServiceDetails(invId);
    }
}
