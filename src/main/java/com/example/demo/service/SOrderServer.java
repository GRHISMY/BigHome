package com.example.demo.service;

import com.example.demo.enpity.LogisticsInfo;
import com.example.demo.enpity.OrderInfo;
import com.example.demo.enpity.OrderItemInfo;

import java.util.List;

public interface SOrderServer {
    List<OrderInfo> getOrder_InfoList(int b_s_id);

    //商品到订单
    int goodsToOrder(OrderInfo order_info, OrderItemInfo order_item_info, LogisticsInfo logistics_info);

    //购物车到订单
    int cartToOrder(List<OrderInfo> order_infoList, LogisticsInfo logistics_info);
}
