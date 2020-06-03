package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.enpity.LogisticsInfo;
import com.example.demo.enpity.OrderInfo;
import com.example.demo.enpity.OrderItemInfo;
import com.example.demo.enpity.StoreItem;
import com.example.demo.mapper.AddressInfoMapper;
import com.example.demo.mapper.Logistics_InfoMapper;
import com.example.demo.mapper.OrderInfoMapper;
import com.example.demo.mapper.OrderItemInfoMapper;
import com.example.demo.service.BuyerCartService;
import com.example.demo.service.SOrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class SOrderServerImpl implements SOrderServer {

    @Autowired
    private OrderInfoMapper order_infoMapper;
    @Autowired
    private OrderItemInfoMapper order_itemMapper;
    @Autowired
    private Logistics_InfoMapper logistics_infoMapper;
    @Autowired
    private AddressInfoMapper address_infoMapper;
    @Autowired
    private BuyerCartService buyerCartService;

    @Override
    public List<OrderInfo> getOrder_InfoList(int b_s_id) {
        return order_infoMapper.getOrder_InfoList(b_s_id);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public int goodsToOrder(OrderInfo order_info, LogisticsInfo logistics_info) {
        int flag = 0;
        //1插入订单总表
        flag = order_infoMapper.insertOrder_Info(order_info);
        int order_id = order_info.getOrder_id();//因为order_id是自增长的，所以只有插入之后才能取到
        List<OrderItemInfo> order_item_infoList = order_info.getOrder_item_infoList();
        for (OrderItemInfo orderItemInfo : order_item_infoList){
            //2:插入订单明细
            orderItemInfo.setOrder_id(order_id);
            orderItemInfo.setGoods_property("A");
            flag = order_itemMapper.insertOrder_Item_Info(orderItemInfo);
        }
        //3:插入物流总表。物流明细由物流点自动添加进去
        logistics_info.setOrder_id(order_id);
        flag = logistics_infoMapper.insertLogistics_Info(logistics_info);



        return  flag;
    }

    @Override
    public int cartToOrder(OrderInfo order_info, LogisticsInfo logistics_info) {
        int flag = 0;
        //1插入订单总表
        flag = order_infoMapper.insertOrder_Info(order_info);
        int order_id = order_info.getOrder_id();//因为order_id是自增长的，所以只有插入之后才能取到
        List<OrderItemInfo> order_item_infoList = order_info.getOrder_item_infoList();
        for (OrderItemInfo orderItemInfo : order_item_infoList){
            //2:插入订单明细
            orderItemInfo.setOrder_id(order_id);
            orderItemInfo.setGoods_property("A");
            flag = order_itemMapper.insertOrder_Item_Info(orderItemInfo);
        }
        //3:插入物流总表。物流明细由物流点自动添加进去
        logistics_info.setOrder_id(order_id);
        flag = logistics_infoMapper.insertLogistics_Info(logistics_info);

        int store_id = order_info.getStore_id();
        for (OrderItemInfo order_item_info : order_info.getOrder_item_infoList()) {
            int goods_id = order_item_info.getGoods_id();
            //删除只需要2个参数
            buyerCartService.deleteStore_Item(goods_id,store_id);
        }

        return  flag;
    }

    @Override
    public List<OrderItemInfo> getOrder_Item_InfoList(int order_id) {
        return order_itemMapper.getOrder_Item_InfoList(order_id);
    }

    public List<StoreItem> buyerCart() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        ArrayList<StoreItem> result = new ArrayList<>();
        result = (ArrayList<StoreItem>) JSONObject.parseArray(jedis.get("buyerCart"),StoreItem.class);
        jedis.close();

        return result;
    }



}
