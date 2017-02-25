package com.springapp.mvc.dto;

import com.springapp.mvc.model.Customer;

import java.io.Serializable;

/**
 * Created by Heshani Samarasekara on 2/18/2017.
 */
public class CustomerDto implements Serializable {
    private String custName;
    private int id;
    private String custNic;
    private String custTp;
    private String custEmail;
    private String custAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustNic() {
        return custNic;
    }

    public void setCustNic(String custNic) {
        this.custNic = custNic;
    }

    public String getCustTp() {
        return custTp;
    }

    public void setCustTp(String custTp) {
        this.custTp = custTp;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
}
