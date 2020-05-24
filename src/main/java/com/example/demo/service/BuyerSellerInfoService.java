package com.example.demo.service;

import com.example.demo.enpity.BuyerSellerInfo;
import com.example.demo.mapper.BuyerSellerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerSellerInfoService {
    @Autowired
    private BuyerSellerInfoMapper buyerSellerInfoMapper;

    public Integer addUser(BuyerSellerInfo buyerSellerInfo){
        return buyerSellerInfoMapper.addUser(buyerSellerInfo);
    }
    public List<BuyerSellerInfo> findAll(){
        return buyerSellerInfoMapper.findAll();
    }
}
