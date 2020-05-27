package com.example.demo.service;

import com.example.demo.enpity.GoodsInfo;
import com.example.demo.enpity.GoodsPhotoPath_Info;
import com.example.demo.enpity.GoodsPropertyInfo;

import java.util.List;

public interface SGoodsInfoService {
    List<GoodsInfo> getGoodsList();

    int insertGoods_Info(GoodsInfo goods_info,
                                List<GoodsPhotoPath_Info> goods_photo_path_infoList,
                                List<GoodsPropertyInfo> goods_property_infoList);
    GoodsInfo getAGoods_Info(int goods_id);
}
