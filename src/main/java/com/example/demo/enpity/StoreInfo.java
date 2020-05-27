package com.example.demo.enpity;

import java.io.Serializable;

public class StoreInfo implements Serializable {
    private Integer store_id;
    private String store_name;
    private Integer b_s_id;
    private String store_describe;
    private Integer credit;
    private Integer link_man_id;

    @Override
    public String toString() {
        return "StoreInfo{" +
                "store_id=" + store_id +
                ", store_name='" + store_name + '\'' +
                ", b_s_id=" + b_s_id +
                ", store_describe='" + store_describe + '\'' +
                ", credit=" + credit +
                ", link_man_id=" + link_man_id +
                '}';
    }

    public StoreInfo() {
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Integer getB_s_id() {
        return b_s_id;
    }

    public void setB_s_id(Integer b_s_id) {
        this.b_s_id = b_s_id;
    }

    public String getStore_describe() {
        return store_describe;
    }

    public void setStore_describe(String store_describe) {
        this.store_describe = store_describe;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getLink_man_id() {
        return link_man_id;
    }

    public void setLink_man_id(Integer link_man_id) {
        this.link_man_id = link_man_id;
    }
}
