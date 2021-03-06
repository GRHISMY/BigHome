package com.example.demo.enpity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfo implements Serializable {
    //用来存储一个订单下面到底买了多少商品。用订单明细list来存储
    private List<OrderItemInfo> order_item_infoList = new ArrayList<OrderItemInfo>();
    private Integer order_id;
    private String order_no;
    private float order_money;
    private Date order_time;
    //store_id知道了卖家是谁。
    private Integer store_id;
    private Integer order_status;
    private String pay_way;
    private Integer pay_status;
    private Date pay_time;
    //b_s_id知道了买家是谁
    private int b_s_id;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "order_item_infoList=" + order_item_infoList +
                ", order_id=" + order_id +
                ", order_no='" + order_no + '\'' +
                ", order_money=" + order_money +
                ", order_time='" + order_time + '\'' +
                ", store_id=" + store_id +
                ", order_status=" + order_status +
                ", pay_way='" + pay_way + '\'' +
                ", pay_status=" + pay_status +
                ", pay_time='" + pay_time + '\'' +
                ", b_s_id=" + b_s_id +
                '}';
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public OrderInfo() {
    }

    public List<OrderItemInfo> getOrder_item_infoList() {
        return order_item_infoList;
    }

    public void setOrder_item_infoList(List<OrderItemInfo> order_item_infoList) {
        this.order_item_infoList = order_item_infoList;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public float getOrder_money() {
        return order_money;
    }

    public void setOrder_money(float order_money) {
        this.order_money = order_money;
    }



    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }



    public int getB_s_id() {
        return b_s_id;
    }

    public void setB_s_id(int b_s_id) {
        this.b_s_id = b_s_id;
    }
}
