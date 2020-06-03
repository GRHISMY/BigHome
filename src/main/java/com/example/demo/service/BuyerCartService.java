package com.example.demo.service;

import com.example.demo.enpity.StoreItem;

import java.util.List;

public interface BuyerCartService {
    List<StoreItem> buyerCart();
    void addStore_Item(int goods_id, int store_id, int goods_sum, float goods_money);
    void deleteStore_Item(int goods_id, int store_id);
    void updateStore_Item(int goods_id, int store_id, int goods_sum, float goods_money);
    void deleteStore( int store_id);
}
