package com.example.demo.mapper;

import com.example.demo.enpity.GoodsPhotoPath_Info;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsPhotoPathInfoMapper {
    @Insert("insert into goods_photo_path_info(path_id, goods_id, path_name) " +
                   "values(#{path_id}, #{goods_id}, #{path_name})")
    int insertGoods_Photo_Path_Info(GoodsPhotoPath_Info goods_photo_path_info);

    @Select("select * from goods_photo_path_info where goods_id = #{goods_id}")
    List<GoodsPhotoPath_Info> getGoods_Photo_Path_List(int goods_id);
}
