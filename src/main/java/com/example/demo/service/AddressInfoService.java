package com.example.demo.service;

import com.example.demo.enpity.AddressInfo;

import java.util.List;

public interface AddressInfoService {
    Integer editDefaultStatus(Integer b_s_id,Integer address_id);
    List<AddressInfo> findAllAddress(Integer b_s_id);

    Integer insertAddress(AddressInfo addressInfo);

    Integer deleteAddress(Integer address_id);

    Integer updataAddressIonfo(AddressInfo addressInfo);

}
