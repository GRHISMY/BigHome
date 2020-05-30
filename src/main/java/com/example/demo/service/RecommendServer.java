package com.example.demo.service;

import com.example.demo.enpity.RecommendGoods;

import java.util.ArrayList;

public interface RecommendServer {
    void addFootPrint(Integer userId,Integer goodsId);
    Integer recommendGoodsId(ArrayList<RecommendGoods> userList);
}
