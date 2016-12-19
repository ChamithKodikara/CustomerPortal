package com.vgates.customerportal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Chamith
 */
@Entity
@Table(name = "MASTER_SERVICE")
public class MasterService implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACTIVE")
    private Boolean active=true;

    @Column(name = "SERVICE_NAME")
    private String serviceName;

    @Column(name = "COST")
    private Double cost;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DISCOUNT")
    private Double discount;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @OneToMany(mappedBy = "masterService")
    private List<CustomerServiceMapper> customerServiceMapperList;

    @OneToMany(mappedBy = "masterService")
    private List<ServiceItemMapper> serviceItemMapperList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MasterService)) {
            return false;
        }
        MasterService other = (MasterService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vgates.customerportal.model.MasterService[ id=" + id + " ]";
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

    public List<CustomerServiceMapper> getCustomerServiceMapperList() {
        return customerServiceMapperList;
    }

    public void setCustomerServiceMapperList(List<CustomerServiceMapper> customerServiceMapperList) {
        this.customerServiceMapperList = customerServiceMapperList;
    }

    public List<ServiceItemMapper> getServiceItemMapperList() {
        return serviceItemMapperList;
    }

    public void setServiceItemMapperList(List<ServiceItemMapper> serviceItemMapperList) {
        this.serviceItemMapperList = serviceItemMapperList;
    }
}
