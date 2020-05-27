package com.example.demo.enpity;

import java.io.Serializable;

public class AddressInfo implements Serializable {
    private Integer address_id;
    private Integer b_s_id;
    private String address;
    private String address_detail;
    private String addresssee;
    private String telephone;
    private Integer default_status;
    private String postcode;

    @Override
    public String toString() {
        return "AddressInfo{" +
                "address_id=" + address_id +
                ", b_s_id=" + b_s_id +
                ", address='" + address + '\'' +
                ", address_detail='" + address_detail + '\'' +
                ", addresssee='" + addresssee + '\'' +
                ", telephone='" + telephone + '\'' +
                ", default_status=" + default_status +
                ", postcode='" + postcode + '\'' +
                '}';
    }

    public AddressInfo() {
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public Integer getB_s_id() {
        return b_s_id;
    }

    public void setB_s_id(Integer b_s_id) {
        this.b_s_id = b_s_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getAddresssee() {
        return addresssee;
    }

    public void setAddresssee(String addresssee) {
        this.addresssee = addresssee;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getDefault_status() {
        return default_status;
    }

    public void setDefault_status(Integer default_status) {
        this.default_status = default_status;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
