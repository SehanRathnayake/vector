package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

@Entity
@Table(name = "T_CUSTOMER")
public class Customer implements Serializable{

    @SequenceGenerator(name = "CUSTOMER_GEN", sequenceName = "CUSTOMERID_SEQ")

    @Id
    @GeneratedValue(generator = "CUSTOMER_GEN")
//    @OneToMany(fetch = FetchType.LAZY)
    @Column  (name = "CUSTOMER_ID")
    private int cus_id;

    @Column  (name = "NAME")
    private String cus_name;

    @Column  (name = "ADDRESS")
    private String cus_address;

    @Column  (name = "PHONE")
    private String cus_phone;

    @Column  (name = "EMAIL")
    private String cus_email;

    /*@OneToMany(fetch = FetchType.EAGER)
   private  Collection<Vehicle> vehicles;
*/
    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }
/*
    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }*/
}
