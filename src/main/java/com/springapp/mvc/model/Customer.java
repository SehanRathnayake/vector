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

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column  (name = "CUSTOMER_ID")
    private long cus_id;

    @Column  (name = "NAME")
    private String cus_name;

    @Column  (name = "ADDRESS")
    private String cus_address;

    @Column  (name = "PHONE")
    private String cus_phone;

    @Column  (name = "EMAIL")
    private String cus_email;

    public long getCustomerid(){return cus_id;}

    public void setCustomerid(long cus_id) {this.cus_id = cus_id;}

    public String getCustomerName(){return cus_name;}

    public void setCustomerName(String cus_name){this.cus_name = cus_name;}

    public String getCustomerAddress(){return cus_address;}

    public void setCustomerAddress(String cus_address){this.cus_address = cus_address;}

    public String getCustomerPhone(){return cus_phone;}

    public void setCustomerPhone(String cus_phone){this.cus_phone=cus_phone;}

    public String getCustomerEmail(){return cus_email;}

    public void setCustomerEmail(String cus_email){this.cus_email = cus_email;}
}
