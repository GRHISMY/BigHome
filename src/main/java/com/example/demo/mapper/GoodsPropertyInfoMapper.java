package com.example.demo.mapper;

import com.example.demo.enpity.GoodsPropertyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsPropertyInfoMapper {
    @Insert("insert into goods_property_info(goods_property_id, goods_id, property_name, property_value) " +
            " values(#{goods_property_id}, #{goods_id}, #{property_name}, #{property_value})")
    int insertGoods_Property(GoodsPropertyInfo goods_property_info);

    @Select("select * from goods_property_info where goods_id = #{goods_id}")
    List<GoodsPropertyInfo> getGoods_Property_List(int goods_id);
}
