package com.example.demo.enpity;

import java.io.Serializable;

public class RecommendGoods implements Serializable {
    private Integer userId;
    private Integer goodsId;
    private Integer footPrintfNumber;


    @Override
    public String toString() {
        return "RecommendGoods{" +
                "userId=" + userId +
                ", goodsId=" + goodsId +
                ", footPrintfNumber=" + footPrintfNumber +
                '}';
    }

    public RecommendGoods() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getFootPrintfNumber() {
        return footPrintfNumber;
    }

    public void setFootPrintfNumber(Integer footPrintfNumber) {
        this.footPrintfNumber = footPrintfNumber;
    }
}
