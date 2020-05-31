package com.example.demo.service.impl;

import com.example.demo.enpity.GoodsInfo;
import com.example.demo.enpity.GoodsPhotoPath_Info;
import com.example.demo.enpity.GoodsPropertyInfo;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.GoodsPhotoPathInfoMapper;
import com.example.demo.mapper.GoodsPropertyInfoMapper;
import com.example.demo.service.SGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SGoodsInfoServiceImpl implements SGoodsInfoService {
    @Autowired
    private GoodsInfoMapper goods_infoMapper;
    @Autowired
    private GoodsPhotoPathInfoMapper goods_photo_path_infoMapper;
    @Autowired
    private GoodsPropertyInfoMapper goods_property_infoMapper;

    @Override
    public List<GoodsInfo> getGoodsList() {
        List<GoodsInfo> goodsList = goods_infoMapper.getGoodsList();
        for (int i = 0; i < goodsList.size(); i++){
            goodsList.get(i).setGoods_photo_path_infoList(goods_photo_path_infoMapper.getGoods_Photo_Path_List(goodsList.get(i).getGoods_id()));
            goodsList.get(i).setGoods_property_infoList(goods_property_infoMapper.getGoods_Property_List(goodsList.get(i).getGoods_id()));
        }
        return goodsList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public int insertGoods_Info(GoodsInfo goods_info, List<GoodsPhotoPath_Info> goods_photo_path_infoList, List<GoodsPropertyInfo> goods_property_infoList) {
        int flag = 0;
        int goods_id = -1;
        //插入goods_info 一条数据
        flag = goods_infoMapper.insertGoods_Info(goods_info);
        goods_id = goods_info.getGoods_id();
        //插入图片路径， 多条数据
        for (GoodsPhotoPath_Info goods_photo_path_info : goods_photo_path_infoList){
            goods_photo_path_info.setGoods_id(goods_id);
            flag = goods_photo_path_infoMapper.insertGoods_Photo_Path_Info(goods_photo_path_info);
        }
        //插入属性值 多条
        for (GoodsPropertyInfo goods_property_info : goods_property_infoList){
            goods_property_info.setGoods_id(goods_id);
            flag = goods_property_infoMapper.insertGoods_Property(goods_property_info);
        }

        return  flag;
    }

    @Override
    public GoodsInfo getAGoods_Info(int goods_id) {
        GoodsInfo aGoods_info = goods_infoMapper.getAGoods_Info(goods_id);
        aGoods_info.setGoods_property_infoList(goods_property_infoMapper.getGoods_Property_List(goods_id));
        aGoods_info.setGoods_photo_path_infoList(goods_photo_path_infoMapper.getGoods_Photo_Path_List(goods_id));
        return aGoods_info;
    }
}
