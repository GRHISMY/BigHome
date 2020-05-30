package com.example.demo.enpity;

import java.io.Serializable;
import java.util.Date;

public class OrderItemInfo implements Serializable {
    private Integer order_item_id;
    private Integer order_id;
    private Date order_item_time;
    private Integer goods_id;
    private Integer goods_sum;
    private float goods_money;
    private String goods_property;

    @Override
    public String toString() {
        return "OrderItemInfo{" +
                "order_item_id=" + order_item_id +
                ", order_id=" + order_id +
                ", order_item_time='" + order_item_time + '\'' +
                ", goods_id=" + goods_id +
                ", goods_sum=" + goods_sum +
                ", goods_money=" + goods_money +
                ", goods_property='" + goods_property + '\'' +
                '}';
    }

    public OrderItemInfo() {
    }

    public Integer getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Integer order_item_id) {
        this.order_item_id = order_item_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_item_time() {
        return order_item_time;
    }

    public void setOrder_item_time(Date order_item_time) {
        this.order_item_time = order_item_time;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getGoods_sum() {
        return goods_sum;
    }

    public void setGoods_sum(Integer goods_sum) {
        this.goods_sum = goods_sum;
    }

    public float getGoods_money() {
        return goods_money;
    }

    public void setGoods_money(float goods_money) {
        this.goods_money = goods_money;
    }

    public String getGoods_property() {
        return goods_property;
    }

    public void setGoods_property(String goods_property) {
        this.goods_property = goods_property;
    }
}
