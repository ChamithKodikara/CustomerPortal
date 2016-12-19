package com.vgates.customerportal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Chamith
 */
@Entity
@Table(name = "CUSTOMER_SERVICE_MAPPER")
public class CustomerServiceMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "CUSTOMER_DETAIL")
    @ManyToOne
    private CustomerDetail customerDetail;

    @JoinColumn(name = "MASTER_SERVICE")
    @ManyToOne
    private MasterService masterService;

    @Column(name = "APPOINTMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public MasterService getMasterService() {
        return masterService;
    }

    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CustomerServiceMapper)) {
            return false;
        }
        CustomerServiceMapper other = (CustomerServiceMapper) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vgates.customerportal.model.CustomerServiceMapper[ id=" + id + " ]";
    }
}
