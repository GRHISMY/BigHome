package com.example.demo.controller;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.enpity.*;
import com.example.demo.enpity.vo.BuyerSellerVO;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.GoodsPhotoPathInfoMapper;
import com.example.demo.mapper.GoodsPropertyInfoMapper;
import com.example.demo.mapper.StoreInfoMapper;
import com.example.demo.service.BuyerCartService;
import com.example.demo.service.BuyerSellerInfoService;
import com.example.demo.service.RecommendServer;
import com.example.demo.service.SGoodsInfoService;
import com.example.demo.util.ConstantKit;
import com.example.demo.util.Md5TokenGenerator;
import com.example.demo.util.UpLoadImg;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class testController {

    @Autowired
    BuyerSellerInfoService buyerSellerInfoService;

    @Autowired
    private Md5TokenGenerator tokenGenerator;

    @Autowired
    StoreInfoMapper storeInfoMapper;

    @Autowired
    SGoodsInfoService sGoodsInfoService;

    @Autowired
    GoodsPropertyInfoMapper goodsPropertyInfoMapper;

    @Autowired
    GoodsPhotoPathInfoMapper goodsPhotoPathInfoMapper;

    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Autowired
    BuyerCartService buyerCartService;
    @Autowired
    RecommendServer recommendServer;

    @RequestMapping("recomm")
    public JsonData recom(){
        Integer integer = recommendServer.recommendGoodsId(1);
        GoodsInfo aGoods_info = sGoodsInfoService.getAGoods_Info(integer);
        List<GoodsInfo> list = new ArrayList<GoodsInfo>();
        list.add(aGoods_info);
        return JsonData.buildSuccess(list);
    }

    @RequestMapping("showRedis")
    public void show(){

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        ArrayList<RecommendGoods> recommendGoodsArrayList = (ArrayList<RecommendGoods>) JSONObject.parseArray(jedis.get("recommendGoodsArrayList"),RecommendGoods.class);
        jedis.close();
        recommendServer.recommendGoodsId(1);
    }

    @RequestMapping("addS")
    public JsonData add(@RequestParam("userId") Integer userId,
                        @RequestParam("goodsId")Integer goodsId){
        recommendServer.addFootPrint(userId,goodsId);
        recommendServer.printredis();
        return JsonData.buildSuccess();
    }

    @RequestMapping("deleteCar")
    public JsonData deleteCar(@RequestParam("goods_id") Integer goods_id,
                              @RequestParam("store_id")Integer store_id
                              ){
        buyerCartService.deleteStore_Item(goods_id,store_id);
        return JsonData.buildSuccess();
    }

    @RequestMapping("updatacar")
    public JsonData updatacar(@RequestParam("goods_id") Integer goods_id,
                              @RequestParam("store_id")Integer store_id,
                              @RequestParam("goods_sum") Integer goods_sum,
                              @RequestParam("goods_money") float goods_money){
        buyerCartService.updateStore_Item(goods_id,store_id,goods_sum,goods_money);
        return JsonData.buildSuccess();
    }

    @RequestMapping("addcar")
    public JsonData addcar(@RequestParam("goods_id") Integer goods_id,
                           @RequestParam("store_id")Integer store_id,
                           @RequestParam("goods_sum")Integer goods_sum,
                           @RequestParam("goods_money") float goods_money){
        buyerCartService.addStore_Item(goods_id,store_id,goods_sum,goods_money);
        return JsonData.buildSuccess();

    }
    @RequestMapping("getCar")
    public JsonData getCar(){
        return JsonData.buildSuccess(buyerCartService.buyerCart());
    }

    @RequestMapping("testRedis")
    public JsonData testRedis(){
        List<StoreItem> store_itemList =new ArrayList<>();
        List<GoodsItem> goodsItemList = new ArrayList<>();
        GoodsItem goodsItem = new GoodsItem();
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setDictionary_code("111");
        goodsInfo.setGoods_describe("BBBB");

        goodsItem.setAccount(1);
        goodsItem.setGoods_money(1);
        goodsItem.setGoodsInfo(goodsInfo);

        goodsItemList.add(goodsItem);

        StoreItem storeItem = new StoreItem();
        storeItem.setStore_id(1);
        storeItem.setLink_man_id(1);
        storeItem.setStore_name("A");

        storeItem.setGoods_itemList(goodsItemList);
        store_itemList.add(storeItem);

        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("26564356");
        jedis.set("testArrayList", String.valueOf(JSONArray.parseArray(JSON.toJSONString(store_itemList))));

        ArrayList<StoreItem> result = new ArrayList<>();
        result = (ArrayList<StoreItem>) JSONObject.parseArray(jedis.get("AAAAAA"),StoreItem.class);
        System.err.println(result);

        jedis.close();

        return JsonData.buildSuccess();
    }

    @RequestMapping("getAGoods_Info")
    public JsonData getAGoods_Info (Integer goods_id){
        GoodsInfo aGoods_info = sGoodsInfoService.getAGoods_Info(goods_id);
        return JsonData.buildSuccess(aGoods_info);
    }

    @RequestMapping("getGoodsList")
    public JsonData getGoodsList(){
        return JsonData.buildSuccess(sGoodsInfoService.getGoodsList());
    }

    @RequestMapping("getstoreByid")
    public JsonData getstoreByid(Integer store_id){
        return JsonData.buildSuccess(storeInfoMapper.getAStoreInfo(store_id));
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public JsonData login(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password){

        Assert.notBlank(username);
        Assert.notBlank(password);
        BuyerSellerInfo buyerSellerInfo = buyerSellerInfoService.finUserByTelephone(username);
        System.err.println(buyerSellerInfo);
        if (buyerSellerInfo.getPwd().equals(password)){
            Jedis jedis = new Jedis("127.0.0.1", 6379);
//        jedis.auth("26564356");
            String token = tokenGenerator.generate(username, password);
            jedis.set(username, password);
            jedis.expire(username, ConstantKit.TOKEN_EXPIRE_TIME);
            jedis.set(token, username);
            jedis.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
            Long currentTime = System.currentTimeMillis();
            jedis.set(token + username, currentTime.toString());
            jedis.close();

            BuyerSellerVO buyerSellerVO = BuyerSellerInfo2BuyerSellerVo(buyerSellerInfo,token);
            return JsonData.buildSuccess(buyerSellerVO, "登录成功！");

        }else {
            return JsonData.buildError("账号或密码错误");
        }



    }

    @AuthToken
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public JsonData findAll(){
        System.err.println(buyerSellerInfoService.findAll());
        return JsonData.buildSuccess(buyerSellerInfoService.findAll());

    }

    @RequestMapping(value ="/fileUp", method = RequestMethod.POST)
    @ResponseBody
    public void fileUploadOne(
                              @RequestParam("bSName") String bSName,
                              @RequestParam("nickName")String nickName,
                              @RequestParam("sex")Integer    sex,
                              @RequestParam("file") MultipartFile file,
                              @RequestParam("telephone")  String   telephone,
                              @RequestParam("pwd")  String   pwd,
                              @RequestParam("home")  String   home,
                              @RequestParam("home_detail")  String   home_detail,
                              @RequestParam("status")  Integer   status,
                              @RequestParam("bSStatus")  Integer   bSStatus,
                              @RequestParam("email")  String   email) {
        UpLoadImg upLoadImg = new UpLoadImg();
        String fileName = upLoadImg.uploadImg(file);
        BuyerSellerInfo buyerSellerInfo = new BuyerSellerInfo();

        buyerSellerInfo.setbSName(bSName);
        buyerSellerInfo.setNickName(nickName);
        buyerSellerInfo.setSex(sex);
        buyerSellerInfo.setIcon(fileName);
        buyerSellerInfo.setTelephone(telephone);
        buyerSellerInfo.setPwd(pwd);
        buyerSellerInfo.setStatus(status);
        buyerSellerInfo.setbSStatus(bSStatus);
        buyerSellerInfo.setEmail(email);
        buyerSellerInfo.setLastLoginTime(new Date());
        buyerSellerInfo.setRegisterTime(new Date());

        buyerSellerInfo.setHome(home);
        buyerSellerInfo.setHome_detail(home_detail);
        Integer resultStatus = buyerSellerInfoService.addUser(buyerSellerInfo);
        System.err.println(resultStatus);

//        System.err.println(fileName);
    }


    public BuyerSellerVO BuyerSellerInfo2BuyerSellerVo(BuyerSellerInfo buyerSellerInfo,String token){
        BuyerSellerVO buyerSellerVO = new BuyerSellerVO();
        buyerSellerVO.setbSId(buyerSellerInfo.getbSId());
        buyerSellerVO.setbSName(buyerSellerInfo.getbSName());
        buyerSellerVO.setNickName(buyerSellerInfo.getNickName());
        buyerSellerVO.setSex(buyerSellerInfo.getSex());
        buyerSellerVO.setIcon(buyerSellerInfo.getIcon());
        buyerSellerVO.setTelephone(buyerSellerInfo.getTelephone());
        buyerSellerVO.setHome(buyerSellerInfo.getHome());
        buyerSellerVO.setHome_detail(buyerSellerInfo.getHome_detail());
        buyerSellerVO.setStatus(buyerSellerInfo.getStatus());
        buyerSellerVO.setbSStatus(buyerSellerInfo.getbSStatus());
        buyerSellerVO.setEmail(buyerSellerInfo.getEmail());
        buyerSellerVO.setLastLoginTime(buyerSellerInfo.getLastLoginTime());
        buyerSellerVO.setRegisterTime(buyerSellerInfo.getRegisterTime());
        buyerSellerVO.setToken(token);

        return buyerSellerVO;

    }


}
