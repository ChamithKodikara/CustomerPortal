package com.vgates.customerportal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Chamith
 */
@Entity
@Table(name = "INVOICE")
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "INVOICE_NO")
    private String invoiceNo;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @Column(name = "DISCOUNT")
    private Double discount;

    @Column(name = "PAID_AMOUNT")
    private Double paidAmount;

    @Column(name = "BALANCE_AMOUNT")
    private Double balanceAmount;

    @Column(name = "FINAL_AMOUNT")
    private Double finalAmount;

    @Column(name = "ACTIVE")
    private Boolean active = true;

    @JoinColumn(name = "CUSTOMER_DETAIL")
    @ManyToOne
    private CustomerDetail customerDetail;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceServiceMapper> invoiceServiceMapperList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public List<InvoiceServiceMapper> getInvoiceServiceMapperList() {
        return invoiceServiceMapperList;
    }

    public void setInvoiceServiceMapperList(List<InvoiceServiceMapper> invoiceServiceMapperList) {
        this.invoiceServiceMapperList = invoiceServiceMapperList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("com.vgates.customerportal.model.Invoice{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
