package com.example.demo.enpity.vo;

public class AddressInfoVO {
    private Integer id;
    private Integer bsId;
    private String name;
    private String tel;
    private String address;
    private Boolean isDefault;
    private String postalCode;


    @Override
    public String toString() {
        return "AddressInfoVO{" +
                "id=" + id +
                ", bsId=" + bsId +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", isDefault=" + isDefault +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getBsId() {
        return bsId;
    }

    public void setBsId(Integer bsId) {
        this.bsId = bsId;
    }

    public AddressInfoVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
