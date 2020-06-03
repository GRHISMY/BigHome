package com.example.demo.controller;

import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.enpity.GoodsItem;
import com.example.demo.enpity.StoreItem;
import com.example.demo.enpity.vo.BuyCarListVO;
import com.example.demo.enpity.vo.BuyCarVO;
import com.example.demo.service.BuyerCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("car")
@CrossOrigin
public class carController {
    @Autowired
    BuyerCartService buyerCartService;

    @AuthToken
    @RequestMapping(value = "deleteItem",method = RequestMethod.POST)
    public JsonData deleteStore_Item(@RequestParam("goods_id") int goods_id,
                                     @RequestParam("store_id") int store_id){
        buyerCartService.deleteStore_Item(goods_id,store_id);
        return JsonData.buildSuccess();
    }

    @AuthToken
    @RequestMapping(value = "deleteStore",method = RequestMethod.POST)
    public JsonData deleteStore(@RequestParam("store_id") int store_id){
        buyerCartService.deleteStore(store_id);
        return JsonData.buildSuccess();
    }

    @AuthToken
    @RequestMapping(value = "addcar",method = RequestMethod.POST)
    public JsonData addcar(@RequestParam("goods_id") Integer goods_id,
                           @RequestParam("store_id")Integer store_id,
                           @RequestParam("goods_sum")Integer goods_sum,
                           @RequestParam("goods_money") float goods_money){
        buyerCartService.addStore_Item(goods_id,store_id,goods_sum,goods_money);
        return JsonData.buildSuccess();

    }
    @AuthToken
    @RequestMapping("getCar")
    public JsonData getCar(){
        List<StoreItem> storeItems = buyerCartService.buyerCart();
        if (storeItems == null){
            return JsonData.buildSuccess();
        }
        List<BuyCarVO> buyCarVOList = po2Vo(storeItems);
        return JsonData.buildSuccess(buyCarVOList);
    }

    @AuthToken
    @RequestMapping(value = "editCar",method = RequestMethod.POST)
    public JsonData editCar(@RequestParam("goods_id") Integer goods_id,
                            @RequestParam("store_id")Integer store_id,
                            @RequestParam("goods_sum")Integer goods_sum,
                            @RequestParam("goods_money") float goods_money){
//        System.err.println(goods_id+" "+goods_sum);
//        System.err.println();
        buyerCartService.updateStore_Item(goods_id,store_id,goods_sum,goods_money);
        return JsonData.buildSuccess();

    }

    private List<BuyCarVO> po2Vo(List<StoreItem> storeItems){
        List<BuyCarVO> buyCarVOList = new ArrayList<BuyCarVO>();
        for (StoreItem storeItem: storeItems){
            BuyCarVO buyCarVO = new BuyCarVO();
            buyCarVO.setSj(storeItem.getStore_name());
            List<BuyCarListVO> items = new ArrayList<BuyCarListVO>();

            for (GoodsItem goodsItem :storeItem.getGoods_itemList()){
                BuyCarListVO buyCarListVO = new BuyCarListVO();
                buyCarListVO.setId(goodsItem.getGoodsInfo().getGoods_id());
                buyCarListVO.setCp(goodsItem.getGoodsInfo().getGoods_name());
                buyCarListVO.setJg(goodsItem.getGoods_money());
                buyCarListVO.setSl(goodsItem.getAccount());
                buyCarListVO.setImg(goodsItem.getGoodsInfo().getGoods_photo_path_infoList().get(0).getPath_name());

                items.add(buyCarListVO);
            }

            buyCarVO.setItems(items);

            buyCarVOList.add(buyCarVO);

        }
        return buyCarVOList;
    }

}
