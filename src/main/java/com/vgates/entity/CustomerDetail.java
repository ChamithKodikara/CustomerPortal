package com.vgates.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Chamith on 11/1/2016.
 */
@Entity
@Table(name = "CUSTOMER_DETAIL")
public class CustomerDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_")
    private Long id;

    @Column(name = "CUSTOMER_NAME_")
    private Long customerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerName() {
        return customerName;
    }

    public void setCustomerName(Long customerName) {
        this.customerName = customerName;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CustomerDetail)) {
            return false;
        }
        CustomerDetail other = (CustomerDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vgates.entity.CustomerDetail[ id=" + id + " ]";
    }
}
