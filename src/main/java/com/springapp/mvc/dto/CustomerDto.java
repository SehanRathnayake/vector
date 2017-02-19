package com.springapp.mvc.dto;

import com.springapp.mvc.model.Customer;

import java.io.Serializable;

/**
 * Created by Heshani Samarasekara on 2/18/2017.
 */
public class CustomerDto implements Serializable {
        private String cust_name;
        private String cust_nic;
        private String cust_tp;
        private String cust_email;
        private String cust_address;
        private Customer c;

    public Customer getCustomer(){
        return this.c;
    }

    public void setCustomer(Customer customer){
        this.c = customer;
    }

       /* public String getCust_name() {
            return cust_name;
        }

        public void setCust_name(String cust_name) {
            this.cust_name = cust_name;
        }

        public String getCust_nic() {
            return cust_nic;
        }

        public void setCust_nic(String cust_nic) {
            this.cust_nic = cust_nic;
        }

        public String getCust_tp() {
        return cust_tp;
    }

        public void getCust_tp(String cust_tp) {
        this.cust_tp = cust_tp;
    }

        public String getCust_email() {
        return cust_email;
    }

        public void setCust_email(String wheelName) {
        this.cust_email = cust_email;
    }

        public String getCust_address() {
        return cust_address;
    }

        public void setCust_address(String wheelName) {
        this.cust_address = cust_address;
    }*/
}
