package com.example.demo.enpity;

import java.io.Serializable;
import java.util.*;

public class RecommendGoods implements Serializable {
    private Integer userId;
    private Map<Integer,Integer> goodsIdAndFootprintfNumber = new HashMap<Integer,Integer>();

    @Override
    public String toString() {
        return "RecommendGoods{" +
                "userId=" + userId +
                ", goodsIdAndFootprintfNumber=" + goodsIdAndFootprintfNumber +
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

    public Map<Integer, Integer> getGoodsIdAndFootprintfNumber() {
        return goodsIdAndFootprintfNumber;
    }

    public void setGoodsIdAndFootprintfNumber(Map<Integer, Integer> goodsIdAndFootprintfNumber) {
        this.goodsIdAndFootprintfNumber = goodsIdAndFootprintfNumber;
    }
}
