package com.example.demo.service.impl;

import com.example.demo.enpity.AddressInfo;
import com.example.demo.mapper.AddressInfoMapper;
import com.example.demo.service.AddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressInfoServiceImpl implements AddressInfoService {
    @Autowired
    AddressInfoMapper addressInfoMapper;



    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Integer editDefaultStatus(Integer b_s_id, Integer address_id) {
        Integer first = addressInfoMapper.editDefaultStatusFirst(b_s_id);
        Integer send = addressInfoMapper.editDefaultStatusSend(address_id);
//        System.err.println("first "+first);
//        System.err.println("send "+send);


        if(first >= 1 && send >= 1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public List<AddressInfo> findAllAddress(Integer b_s_id) {
        return addressInfoMapper.findAllAddress(b_s_id);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Integer insertAddress(AddressInfo addressInfo) {
        return addressInfoMapper.insertAddress(addressInfo);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Integer insertDefaultAddress(AddressInfo addressInfo) {
        Integer first = addressInfoMapper.editDefaultStatusFirst(addressInfo.getB_s_id());
        Integer send = addressInfoMapper.insertAddress(addressInfo);
        if(first >= 1 && send >= 1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public AddressInfo findOne(Integer address_id) {
        return addressInfoMapper.findOne(address_id);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Integer deleteAddress(Integer address_id) {
        return addressInfoMapper.deleteAddress(address_id);
    }

//    @Override
//    public Integer updataAddressIonfo(AddressInfo addressInfo) {
//        return addressInfoMapper.updataAddressIonfo(addressInfo);
//    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public Integer updataAddressIonfo(AddressInfo addressInfo) {
        if (addressInfo.getDefault_status()== 0){
            Integer first = addressInfoMapper.editDefaultStatusFirst(addressInfo.getB_s_id());
            return addressInfoMapper.updataAddressIonfo(addressInfo);
        }else {
            return addressInfoMapper.updataAddressIonfo(addressInfo);
        }
    }

    @Override
    public Integer findDefaultStatus(Integer b_s_id) {
        return addressInfoMapper.findDefaultStatus(b_s_id);
    }
}
