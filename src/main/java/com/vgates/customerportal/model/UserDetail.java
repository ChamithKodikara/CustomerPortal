package com.vgates.customerportal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chamith on 11/15/2016.
 */

@Entity
@Table(name = "USER_DETAIL")
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LOCK")
    private Boolean lock;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "LAST_ACTIVITY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getLastActDate() {
        return lastActDate;
    }

    public void setLastActDate(Date lastActDate) {
        this.lastActDate = lastActDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserDetail)) {
            return false;
        }
        UserDetail other = (UserDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vgates.customerportal.model.UserDetail[ id=" + id + " ]";
    }

}
