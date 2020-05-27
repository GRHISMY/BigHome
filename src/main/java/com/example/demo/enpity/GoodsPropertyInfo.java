package com.example.demo.enpity;

import java.io.Serializable;

public class GoodsPropertyInfo implements Serializable {
    private Integer goods_property_id;
    private Integer goods_id;
    private String property_name;
    private String property_value;

    @Override
    public String toString() {
        return "GoodsPropertyInfo{" +
                "goods_property_id=" + goods_property_id +
                ", goods_id=" + goods_id +
                ", property_name='" + property_name + '\'' +
                ", property_value='" + property_value + '\'' +
                '}';
    }

    public Integer getGoods_property_id() {
        return goods_property_id;
    }

    public void setGoods_property_id(Integer goods_property_id) {
        this.goods_property_id = goods_property_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public String getProperty_value() {
        return property_value;
    }

    public void setProperty_value(String property_value) {
        this.property_value = property_value;
    }
}
