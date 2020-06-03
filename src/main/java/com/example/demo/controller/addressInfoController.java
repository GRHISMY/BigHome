package com.example.demo.controller;

import com.example.demo.controller.annotation.AuthToken;
import com.example.demo.domain.JsonData;
import com.example.demo.enpity.AddressInfo;
import com.example.demo.enpity.vo.AddressInfoVO;
import com.example.demo.service.AddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("address")
@CrossOrigin
public class addressInfoController {

    @Autowired
    AddressInfoService addressInfoService;

    @AuthToken
    @RequestMapping(value = "findOneAddressInfo",method = RequestMethod.GET)
    public JsonData findOneAddressInfo(@RequestParam(value = "address_id") Integer address_id){
        AddressInfo one = addressInfoService.findOne(address_id);
        AddressInfoVO addressInfoVO = add2VO(one);
        return JsonData.buildSuccess(addressInfoVO,one.getAddress_detail());
    }

    @AuthToken
    @RequestMapping(value = "updataAddressInfoDefault",method = RequestMethod.POST)
    public JsonData updataAddressInfoDefault(@RequestParam(value = "b_s_id") Integer b_s_id,
                                      @RequestParam(value = "address_id") Integer address_id){
        Integer status = addressInfoService.editDefaultStatus(b_s_id, address_id);
        System.err.println("Controller "+status);
        if (status ==1){
            return JsonData.buildSuccess();
        }else {
            return JsonData.buildError("更新错误");
        }
    }

    @AuthToken
    @RequestMapping(value = "updataAddressInfo",method = RequestMethod.POST)
    public JsonData updataAddressInfo(@RequestParam("address_id")Integer address_id,
                                      @RequestParam(value = "b_s_id",required = true) Integer b_s_id,
                                      @RequestParam(value = "address",required = false)String address,
                                      @RequestParam(value = "address_detail",required = false)String    address_detail,
                                      @RequestParam(value = "addresssee",required = false) String addresssee,
                                      @RequestParam(value = "telephone",required = false)  String   telephone,
                                      @RequestParam(value = "default_status",required = false)  Integer   default_status,
                                      @RequestParam(value = "postcode",required = false)  String   postcode){

        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddress_id(address_id);
        addressInfo.setB_s_id(b_s_id);
        addressInfo.setAddress(address);
        addressInfo.setAddress_detail(address_detail);
        addressInfo.setAddresssee(addresssee);
        addressInfo.setTelephone(telephone);
        addressInfo.setDefault_status(default_status);
        addressInfo.setPostcode(postcode);

        Integer status = addressInfoService.updataAddressIonfo(addressInfo);
//        System.err.println("Controller "+status);
        if (status ==1){
            return JsonData.buildSuccess("更新成功");
        }else {
            return JsonData.buildError("更新错误");
        }
    }

    @RequestMapping(value = "getAllAddress",method = RequestMethod.POST)
    @AuthToken
    public JsonData getAllAddress(@RequestParam(value = "b_s_id") Integer b_s_id){
        List<AddressInfo> allAddress = addressInfoService.findAllAddress(b_s_id);
//        System.err.println(allAddress);
//        System.err.println(allAddress.size());
        List<AddressInfoVO> addressInfoVOList = new ArrayList<AddressInfoVO>();
        for (AddressInfo addressInfo: allAddress){
            AddressInfoVO addressInfoVO = add2VO(addressInfo);

            addressInfoVOList.add(addressInfoVO);
        }
        return JsonData.buildSuccess(addressInfoVOList);
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
//        System.err.println(addresssee);
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
            if (default_status == 0){
                Integer integer = addressInfoService.insertDefaultAddress(addressInfo);
                if (integer == 1){
                    return JsonData.buildSuccess();
                }else {
                    return JsonData.buildError("新增失败");
                }
            }else {
                Integer integer = addressInfoService.insertAddress(addressInfo);
                if (integer == 1){
                    return JsonData.buildSuccess();
                }else {
                    return JsonData.buildError("新增失败");
                }
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

    public AddressInfoVO add2VO(AddressInfo addressInfo){



        AddressInfoVO addressInfoVO = new AddressInfoVO();


        if ( addressInfo.getDefault_status() == 0 ){
            addressInfoVO.setIsDefault(true);
        }else {
            addressInfoVO.setIsDefault(false);
        }
        addressInfoVO.setId(addressInfo.getAddress_id());
        addressInfoVO.setBsId(addressInfo.getB_s_id());
        addressInfoVO.setName(addressInfo.getAddresssee());
        addressInfoVO.setTel(addressInfo.getTelephone());
        addressInfoVO.setAddress(addressInfo.getAddress()+" "+addressInfo.getAddress_detail());
        addressInfoVO.setPostalCode(addressInfo.getPostcode());

        return addressInfoVO;
    }

}
