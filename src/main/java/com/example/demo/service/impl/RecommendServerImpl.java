package com.example.demo.service.impl;

import com.example.demo.enpity.RecommendGoods;
import com.example.demo.service.RecommendServer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecommendServerImpl implements RecommendServer {
    @Override
    public void addFootPrint(Integer userId, Integer goodsId) {

    }

    @Override
    public Integer recommendGoodsId(ArrayList<RecommendGoods> userList) {
        return null;
    }
}
