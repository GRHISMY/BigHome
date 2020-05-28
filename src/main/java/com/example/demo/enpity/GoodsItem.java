package com.example.demo.enpity;

import java.io.Serializable;

public class GoodsItem implements Serializable {
    private Integer account = 0;
    private float goods_money = 0;
    private GoodsInfo goodsInfo;

    public GoodsItem() {
    }

    @Override
    public String toString() {
        return "GoodsItem{" +
                "account=" + account +
                ", goods_money=" + goods_money +
                ", goodsInfo=" + goodsInfo +
                '}';
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public float getGoods_money() {
        return goods_money;
    }

    public void setGoods_money(float goods_money) {
        this.goods_money = goods_money;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
}
