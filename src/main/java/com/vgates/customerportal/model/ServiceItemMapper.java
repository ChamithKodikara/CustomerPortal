package com.vgates.customerportal.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Chamith
 */

@Entity
@Table(name = "SERVICE_ITEM_MAPPER")
public class ServiceItemMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "MASTER_SERVICE")
    @ManyToOne
    private MasterService masterService;

    @JoinColumn(name = "MASTER_ITEM")
    @ManyToOne
    private MasterItem masterItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MasterService getMasterService() {
        return masterService;
    }

    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }

    public MasterItem getMasterItem() {
        return masterItem;
    }

    public void setMasterItem(MasterItem masterItem) {
        this.masterItem = masterItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ServiceItemMapper)) {
            return false;
        }
        ServiceItemMapper other = (ServiceItemMapper) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vgates.customerportal.model.ServiceItemMapper[ id=" + id + " ]";
    }
}
