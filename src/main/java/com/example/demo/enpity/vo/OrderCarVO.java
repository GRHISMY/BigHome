package com.example.demo.enpity.vo;

import java.util.ArrayList;
import java.util.List;

public class OrderCarVO {
    private Integer orderId;
    private String sj;
    private boolean select = false;
    private boolean show = true;
    private float order_money;
    private List<BuyCarListVO> items = new ArrayList<BuyCarListVO>();


    @Override
    public String toString() {
        return "OrderCarVO{" +
                "orderId=" + orderId +
                ", sj='" + sj + '\'' +
                ", select=" + select +
                ", show=" + show +
                ", order_money=" + order_money +
                ", items=" + items +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public float getOrder_money() {
        return order_money;
    }

    public void setOrder_money(float order_money) {
        this.order_money = order_money;
    }

    public OrderCarVO() {
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<BuyCarListVO> getItems() {
        return items;
    }

    public void setItems(List<BuyCarListVO> items) {
        this.items = items;
    }
}
