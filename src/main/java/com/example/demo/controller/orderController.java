package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.enpity.GoodsInfo;
import com.example.demo.enpity.LogisticsInfo;
import com.example.demo.enpity.OrderInfo;
import com.example.demo.enpity.OrderItemInfo;
import com.example.demo.enpity.vo.BuyCarListVO;
import com.example.demo.enpity.vo.BuyCarVO;
import com.example.demo.enpity.vo.GoodsVO;
import com.example.demo.enpity.vo.OrderCarVO;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.service.SGoodsInfoService;
import com.example.demo.service.SOrderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("order")
@CrossOrigin
public class orderController {

    @Autowired
    SOrderServer sOrderServer;
    @Autowired
    SGoodsInfoService sGoodsInfoService;

    @AuthToken
    @RequestMapping(value = "getOrderItem",method = RequestMethod.GET)
    public JsonData getOrderItem(@RequestParam("order_id")Integer order_id){
        List<OrderItemInfo> order_item_infoList = sOrderServer.getOrder_Item_InfoList(order_id);
        List<GoodsVO> list = new ArrayList<GoodsVO>();
        for (OrderItemInfo orderItemInfo : order_item_infoList){
            GoodsInfo aGoods_info = sGoodsInfoService.getAGoods_Info(orderItemInfo.getGoods_id());
            GoodsVO goodsVO = PO2VO(aGoods_info, orderItemInfo);
            list.add(goodsVO);
        }

        return JsonData.buildSuccess(list);
    }

    @AuthToken
    @RequestMapping(value = "getAllOrder",method = RequestMethod.GET)
    public JsonData getAllOrder(@RequestParam("b_s_id")Integer b_s_id){
        List<OrderInfo> order_infoList = sOrderServer.getOrder_InfoList(b_s_id);
        int index = -1;
        for (OrderInfo orderInfo : order_infoList){
            index++;
            List<OrderItemInfo> order_item_infoList = sOrderServer.getOrder_Item_InfoList(orderInfo.getOrder_id());
            order_infoList.get(index).setOrder_item_infoList(order_item_infoList);
        }
        List<OrderCarVO> orderCarVOS = allOrder2VO(order_infoList);
        return JsonData.buildSuccess(orderCarVOS);
    }

    @RequestMapping("addOrder")
    public JsonData addOrder(@RequestParam("order_money") float order_money,
                             @RequestParam("store_id") Integer store_id,
                             @RequestParam("b_s_id") Integer b_s_id,
                             @RequestParam("order_list")String order_list,
                             @RequestParam("link_man")String link_man,
                             @RequestParam("link_telephone")String link_telephone,
//                             @RequestParam("goods_address")String goods_address,
                             @RequestParam("address_1")String address_1,
                             @RequestParam("address_detail") String address_detail){
//        List<OrderItemInfo> orderItemInfos
//        List<OrderItemInfo> orderItemInfos = JSON.parseObject(request.getParameter("order_list"), (Type) OrderItemInfo.class);
        String decode = URLDecoder.decode(order_list);
        List<OrderItemInfo> orderItemInfos = JSON.parseArray(decode, OrderItemInfo.class);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_item_infoList(orderItemInfos);
        Date date = new Date();

        orderInfo.setOrder_no("1");
        orderInfo.setOrder_money(order_money);
        orderInfo.setOrder_time(date);
        orderInfo.setStore_id(store_id);
        orderInfo.setOrder_status(0);
        orderInfo.setPay_way("A");
        orderInfo.setPay_status(0);
        orderInfo.setPay_time(date);
        orderInfo.setB_s_id(b_s_id);

        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setLink_man(link_man);
        logisticsInfo.setLink_telephone(link_telephone);
        logisticsInfo.setGoods_address("AAA");
        logisticsInfo.setAddress_1(address_1);
        logisticsInfo.setAddress_detail(address_detail);
        logisticsInfo.setLogistics_status(0);

        sOrderServer.cartToOrder(orderInfo,logisticsInfo);

//        System.err.println(orderItemInfos);
        return JsonData.buildSuccess();
    }

    @RequestMapping("addOrderOne")
    public JsonData addOrderOne(@RequestParam("order_money") float order_money,
                             @RequestParam("store_id") Integer store_id,
                             @RequestParam("b_s_id") Integer b_s_id,
                             @RequestParam("order_list")String order_list,
                             @RequestParam("link_man")String link_man,
                             @RequestParam("link_telephone")String link_telephone,
//                             @RequestParam("goods_address")String goods_address,
                             @RequestParam("address_1")String address_1,
                             @RequestParam("address_detail") String address_detail){
//        List<OrderItemInfo> orderItemInfos
//        List<OrderItemInfo> orderItemInfos = JSON.parseObject(request.getParameter("order_list"), (Type) OrderItemInfo.class);
        String decode = URLDecoder.decode(order_list);
        List<OrderItemInfo> orderItemInfos = JSON.parseArray(decode, OrderItemInfo.class);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrder_item_infoList(orderItemInfos);
        Date date = new Date();

        orderInfo.setOrder_no("1");
        orderInfo.setOrder_money(order_money);
        orderInfo.setOrder_time(date);
        orderInfo.setStore_id(store_id);
        orderInfo.setOrder_status(0);
        orderInfo.setPay_way("A");
        orderInfo.setPay_status(0);
        orderInfo.setPay_time(date);
        orderInfo.setB_s_id(b_s_id);

        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setLink_man(link_man);
        logisticsInfo.setLink_telephone(link_telephone);
        logisticsInfo.setGoods_address("AAA");
        logisticsInfo.setAddress_1(address_1);
        logisticsInfo.setAddress_detail(address_detail);
        logisticsInfo.setLogistics_status(0);

        sOrderServer.goodsToOrder(orderInfo,logisticsInfo);

//        System.err.println(orderItemInfos);
        return JsonData.buildSuccess();
    }

    private GoodsVO PO2VO(GoodsInfo goodsInfo,OrderItemInfo orderItemInfo){
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setNum(orderItemInfo.getGoods_sum());
        goodsVO.setPrice(goodsInfo.getGoods_price());
        goodsVO.setThumb(goodsInfo.getGoods_photo_path_infoList().get(0).getPath_name());
        goodsVO.setTitle(goodsInfo.getGoods_name());

        return goodsVO;
    }

    private List<OrderCarVO> allOrder2VO(List<OrderInfo> order_infoList){

        List<OrderCarVO> list = new ArrayList<OrderCarVO>();
        for (OrderInfo orderInfo : order_infoList){
            OrderCarVO buyCarVO = new OrderCarVO();
            buyCarVO.setOrderId(orderInfo.getOrder_id());
            buyCarVO.setSj("阳光华丽旗舰店");
            buyCarVO.setOrder_money(orderInfo.getOrder_money());
            List<BuyCarListVO> items = new ArrayList<BuyCarListVO>();

            for (OrderItemInfo orderItemInfo: orderInfo.getOrder_item_infoList()){
                GoodsInfo aGoods_info = sGoodsInfoService.getAGoods_Info(orderItemInfo.getGoods_id());
                BuyCarListVO buyCarListVO = new BuyCarListVO();
                buyCarListVO.setId(aGoods_info.getGoods_id());
                buyCarListVO.setImg(aGoods_info.getGoods_photo_path_infoList().get(0).getPath_name());
                buyCarListVO.setSl(orderItemInfo.getGoods_sum());
                buyCarListVO.setJg(orderItemInfo.getGoods_money());
                buyCarListVO.setCp(aGoods_info.getGoods_name());

                items.add(buyCarListVO);
            }
            buyCarVO.setItems(items);

            list.add(buyCarVO);
        }
        return list;



    }
}
