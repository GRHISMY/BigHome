package com.example.demo.enpity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreItem implements Serializable {
    private Integer store_id;
    private String store_name;
    private Integer link_man_id;
    private List<GoodsItem> goods_itemList = new ArrayList<GoodsItem>();





    public void AddGoods_Item(GoodsItem goods_item){
        goods_itemList.add(goods_item);
    }
    public void removeGoods_Item(int index){
        goods_itemList.remove(index);
    }

    public void removeGoods_Item(GoodsItem goods_item){
        goods_itemList.remove(goods_item);
    }

    public void removeGoods_ItemByGoods_id(int goods_id){
        //根据goods_id找到要删除的商品.
        for (GoodsItem goods_item: this.goods_itemList) {
            if (goods_item.getGoodsInfo().getGoods_id() == goods_id){
                //查找到要删除的商品之后.
                goods_itemList.remove(goods_item);
                break;
            }
        }
    }

    public StoreItem() {
    }

    @Override
    public String toString() {
        return "StoreItem{" +
                "store_id=" + store_id +
                ", store_name='" + store_name + '\'' +
                ", link_man_id=" + link_man_id +
                ", goods_itemList=" + goods_itemList +
                '}';
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Integer getLink_man_id() {
        return link_man_id;
    }

    public void setLink_man_id(Integer link_man_id) {
        this.link_man_id = link_man_id;
    }

    public List<GoodsItem> getGoods_itemList() {
        return goods_itemList;
    }

    public void setGoods_itemList(List<GoodsItem> goods_itemList) {
        this.goods_itemList = goods_itemList;
    }
}
