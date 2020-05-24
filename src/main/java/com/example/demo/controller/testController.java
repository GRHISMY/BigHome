package com.example.demo.controller;

import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.enpity.BuyerSellerInfo;
import com.example.demo.service.BuyerSellerInfoService;
import com.example.demo.util.ConstantKit;
import com.example.demo.util.Md5TokenGenerator;
import com.example.demo.util.UpLoadImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import java.util.Date;

@RestController
public class testController {

    @Autowired
    BuyerSellerInfoService buyerSellerInfoService;

    @Autowired
    private Md5TokenGenerator tokenGenerator;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public JsonData login(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password){
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

        return JsonData.buildSuccess(token, "登录成功！");
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


}
