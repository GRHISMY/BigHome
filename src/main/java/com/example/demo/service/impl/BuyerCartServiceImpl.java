package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.enpity.GoodsInfo;
import com.example.demo.enpity.GoodsItem;
import com.example.demo.enpity.StoreInfo;
import com.example.demo.enpity.StoreItem;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.StoreInfoMapper;
import com.example.demo.service.BuyerCartService;
import com.example.demo.service.SGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuyerCartServiceImpl implements BuyerCartService {

    @Autowired
    private GoodsInfoMapper goods_infoMapper;
    @Autowired
    private StoreInfoMapper store_infoMapper;

    @Autowired
    private SGoodsInfoService sGoodsInfoService;

    private List<StoreItem> store_itemList = new ArrayList<StoreItem>();

    public BuyerCartServiceImpl() {
    }

    public void addStore_Item(StoreItem store_item){
        store_itemList.add(store_item);
    }

    public int findStore(int store_id){
        int store_index = -1;
        int i = 0;
        for (StoreItem  item : this.store_itemList) {
            if (item.getStore_id() == store_id){
                store_index = i;
                break;
            }
            i++;
        }
        return store_index;
    }

    private Integer findGoods(int store_item_index, int goods_id) {
        this.store_itemList = buyerCart();
        if (store_itemList == null || store_itemList.size() == 0){
            this.store_itemList = new ArrayList<StoreItem>();
        }

        List<GoodsItem> goodsItemList1 = this.store_itemList.get(store_item_index).getGoods_itemList();
        int good_index = -1;
        int i = 0;
        for (GoodsItem item : goodsItemList1){
            if (item.getGoodsInfo().getGoods_id() == goods_id){
                good_index = i;
            }
            i++;
        }

        return good_index;
    }

    @Override
    public List<StoreItem> buyerCart() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        ArrayList<StoreItem> result = new ArrayList<StoreItem>();
        result = (ArrayList<StoreItem>) JSONObject.parseArray(jedis.get("buyerCart"),StoreItem.class);
        jedis.close();

        return result;
    }

    @Override
    public void addStore_Item(int goods_id, int store_id, int goods_sum, float goods_money) {
        this.store_itemList = buyerCart();
        if (store_itemList == null || store_itemList.size() == 0){
            this.store_itemList = new ArrayList<StoreItem>();
        }
        int store_item_index = -1;
        int goods_item_index = -1;
        GoodsInfo goodsInfo = sGoodsInfoService.getAGoods_Info(goods_id);

        store_item_index = findStore(store_id);
        // 如果没有店铺 那么先构造商品 然后再构造店铺
        if (store_item_index == -1){
            //构造一个商品对象
            GoodsItem goods_item = new GoodsItem();
            goods_item.setGoodsInfo(goodsInfo); //设置商品
            goods_item.setAccount(goods_sum);
            goods_item.setGoods_money(goods_money);
            //构造一个店铺对象
            StoreItem store_item = new StoreItem();
            StoreInfo store_info = store_infoMapper.getAStoreInfo(store_id);
            store_item.setStore_id(store_info.getStore_id());
            store_item.setStore_name(store_info.getStore_name());
            store_item.setLink_man_id(store_info.getLink_man_id());

            //把商品加入到店铺
            store_item.AddGoods_Item(goods_item);
            //把店铺加入到购物车。
            this.store_itemList.add(store_item);
        }else if (store_item_index >= 0){
            goods_item_index = findGoods(store_item_index,goods_id);
            System.err.println("IN   "+goods_item_index);
            if (goods_item_index == -1) {
                //说明店铺存在，但是商品不存在，则应该则该店铺下添加该商品
                GoodsItem goods_item = new GoodsItem();
                goods_item.setGoodsInfo(goodsInfo); //设置商品
                goods_item.setAccount(goods_sum);
                goods_item.setGoods_money(goods_money);
                //把商品加入到店铺，店铺本身就在购物车，所以不需要把店铺加入到购物车
                this.store_itemList.get(store_item_index).AddGoods_Item(goods_item);
            }else {//店铺存在，商品也存在
                //this.store_itemList.get(store_item_index).getGoods_itemList().get(goods_item_index)
                //找到店铺，找到商品
                this.store_itemList.get(store_item_index).getGoods_itemList().get(goods_item_index).
                        setAccount(goods_sum + this.store_itemList.get(store_item_index).
                                getGoods_itemList().
                                get(goods_item_index).getAccount()); //设置数量=原来的数量+新增加的数量
                this.store_itemList.get(store_item_index).getGoods_itemList().get(goods_item_index).
                        setGoods_money(goods_money + this.store_itemList.get(store_item_index).
                                getGoods_itemList().
                                get(goods_item_index).getGoods_money()); //设置金额=原来的金额+新增加的金额
            }


        }

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("buyerCart", String.valueOf(JSONArray.parseArray(JSON.toJSONString(store_itemList))));
        jedis.close();
    }



    @Override
    public void deleteStore_Item(int goods_id, int store_id) {
        this.store_itemList = buyerCart();
        int store_item_index = this.findStore(store_id);
        int goods_item_index = findGoods(store_item_index,goods_id);
//        int goods_item_index = this.store_itemList.get(store_item_index).findGood_Item(goods_id);
        //1:店铺下只有一种商品。则删除店铺即可
        if (this.store_itemList.get(store_item_index).getGoods_itemList().size() == 1){
            this.store_itemList.remove(store_item_index);
        }
        else {
            //2:店铺下有多种商品，则需要删除商品，店铺不能删除
            this.store_itemList.get(store_item_index).getGoods_itemList().remove(goods_item_index);
        }

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("buyerCart", String.valueOf(JSONArray.parseArray(JSON.toJSONString(store_itemList))));

        jedis.close();
    }

    @Override
    public void updateStore_Item(int goods_id, int store_id, int goods_sum, float goods_money) {
        this.store_itemList = buyerCart();
        int store_item_index = this.findStore(store_id);
//        int goods_item_index = this.store_itemList.get(store_item_index).findGood_Item(goods_id);
        int goods_item_index = findGoods(store_item_index,goods_id);
        this.store_itemList.get(store_item_index).getGoods_itemList().get(goods_item_index)
                .setAccount(goods_sum);
        this.store_itemList.get(store_item_index).getGoods_itemList().get(goods_item_index)
                .setGoods_money(goods_money);

//        System.err.println("goods_item_index "+goods_item_index);
//        System.err.println("store_item_index "+store_item_index);

//        System.err.println("goods_id "+goods_id);
//        System.err.println("goods_sum "+goods_sum);
//        System.err.println(store_itemList);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("buyerCart", String.valueOf(JSONArray.parseArray(JSON.toJSONString(store_itemList))));
        jedis.close();
    }

    @Override
    public void deleteStore(int store_id) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.del("buyerCart");
        jedis.close();
    }
}
