package com.springapp.mvc.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Sehan Rathnayake on 16/08/04.
 */
@Entity
@Table(name = "T_ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = 1960820966066484915L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private long roleId;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_ROLE_PRIVILEGE",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRIVILEGE_ID"))
    private Collection<Privilege> privileges;


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

}
