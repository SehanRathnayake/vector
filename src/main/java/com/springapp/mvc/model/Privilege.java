package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Sehan Rathnayake on 16/08/04.
 */


@Entity
@Table(name = "T_PRIVILEGE")
public class Privilege implements Serializable {

    private static final long serialVersionUID = 1960820966066482915L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVILEGE_ID")
    private long privilegeId;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
