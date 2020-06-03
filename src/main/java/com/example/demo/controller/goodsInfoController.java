package com.example.demo.controller;

import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.enpity.GoodsInfo;
import com.example.demo.enpity.vo.BuyCarVO;
import com.example.demo.service.SGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goods")
@CrossOrigin
public class goodsInfoController {
    @Autowired
    SGoodsInfoService sGoodsInfoService;

    @AuthToken
    @RequestMapping(value = "getGoodsList",method = RequestMethod.GET)
    public JsonData getGoodsList(){
        return JsonData.buildSuccess(sGoodsInfoService.getGoodsList());
    }

    @AuthToken
    @RequestMapping("getAGoods_Info")
    public JsonData getAGoods_Info (@RequestParam(value = "goods_id") Integer goods_id){
        GoodsInfo aGoods_info = sGoodsInfoService.getAGoods_Info(goods_id);
        return JsonData.buildSuccess(aGoods_info);
    }


}
