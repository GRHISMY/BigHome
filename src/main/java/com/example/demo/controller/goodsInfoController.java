package com.example.demo.controller;

import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.service.SGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("goods")
public class goodsInfoController {
    @Autowired
    SGoodsInfoService sGoodsInfoService;

    @AuthToken
    @RequestMapping(value = "getGoodsList",method = RequestMethod.GET)
    public JsonData getGoodsList(){
        return JsonData.buildSuccess(sGoodsInfoService.getGoodsList());
    }
}
