package com.vgates.customerportal.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Chamith
 */
@Entity
@Table(name = "INVOICE_SERVICE_MAPPER")
public class InvoiceServiceMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "INVOICE")
    @ManyToOne
    private Invoice invoice;

    @JoinColumn(name = "MASTER_SERVICE")
    @ManyToOne
    private MasterService masterService;

    @Column(name = "MASTER_SERVICE_COST")
    private Double masterServiceCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public MasterService getMasterService() {
        return masterService;
    }

    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    public Double getMasterServiceCost() {
        return masterServiceCost;
    }

    public void setMasterServiceCost(Double masterServiceCost) {
        this.masterServiceCost = masterServiceCost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InvoiceServiceMapper)) {
            return false;
        }
        InvoiceServiceMapper other = (InvoiceServiceMapper) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vgates.customerportal.model.InvoiceServiceMapper[ id=" + id + " ]";
    }
}
