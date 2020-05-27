package com.example.demo.controller;

import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.enpity.AddressInfo;
import com.example.demo.service.AddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("address")
public class addressInfoController {

    @Autowired
    AddressInfoService addressInfoService;

    @AuthToken
    @RequestMapping(value = "updataAddressInfo",method = RequestMethod.POST)
    public JsonData updataAddressInfo(@RequestParam(value = "b_s_id") Integer b_s_id,
                                      @RequestParam(value = "address_id") Integer address_id){
        Integer status = addressInfoService.editDefaultStatus(b_s_id, address_id);
        System.err.println("Controller "+status);
        if (status ==1){
            return JsonData.buildSuccess();
        }else {
            return JsonData.buildError("更新错误");
        }
    }

    @RequestMapping(value = "getAllAddress",method = RequestMethod.POST)
    @AuthToken
    public JsonData getAllAddress(@RequestParam(value = "b_s_id") Integer b_s_id){
        List<AddressInfo> allAddress = addressInfoService.findAllAddress(b_s_id);
        System.err.println(allAddress);
        System.err.println(allAddress.size());
        return JsonData.buildSuccess(allAddress);
    }

    @RequestMapping(value = "addAddress",method = RequestMethod.POST)
    @AuthToken
    public JsonData addAddress(
            @RequestParam("b_s_id") Integer b_s_id,
            @RequestParam("address")String address,
            @RequestParam("address_detail")String    address_detail,
            @RequestParam("addresssee") String addresssee,
            @RequestParam("telephone")  String   telephone,
            @RequestParam("default_status")  Integer   default_status,
            @RequestParam("postcode")  String   postcode

    ){
        List<AddressInfo> allAddress = addressInfoService.findAllAddress(b_s_id);
        if (allAddress.size() <10){
            AddressInfo addressInfo = new AddressInfo();
            addressInfo.setB_s_id(b_s_id);
            addressInfo.setAddress(address);
            addressInfo.setAddress_detail(address_detail);
            addressInfo.setAddresssee(addresssee);
            addressInfo.setTelephone(telephone);
            addressInfo.setDefault_status(default_status);
            addressInfo.setPostcode(postcode);

            Integer integer = addressInfoService.insertAddress(addressInfo);
            if (integer == 1){
                return JsonData.buildSuccess();
            }else {
                return JsonData.buildError("新增失败");
            }
        }else {
            return JsonData.buildError("地址不能超过十条");
        }

    }

    @RequestMapping(value = "deleteAddress",method = RequestMethod.POST)
    @AuthToken
    public JsonData deleteAddress(@RequestParam(value = "address_id") Integer address_id){
        Integer integer = addressInfoService.deleteAddress(address_id);
        if (integer == 1){
            return JsonData.buildSuccess();
        }else {
            return JsonData.buildError("删除失败");
        }
    }

}
