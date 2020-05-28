package com.example.demo.mapper;

import com.example.demo.enpity.GoodsInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsInfoMapper {
    @Insert("insert into goods_info(goods_name, goods_describe, goods_price, " +
            " goods_actual_price,goods_stock, store_id, dictionary_code) values(" +
            "#{goods_name},#{goods_describe},#{goods_price},#{goods_actual_price}," +
            "#{goods_stock},#{store_id},#{dictionary_code})")
//    @Options(useGeneratedKeys = true, keyProperty = "goods_id")
    int insertGoods_Info(GoodsInfo goods_info);

    @Select("select * from goods_info where goods_id = #{goods_id}")
//    @Results({//对单表本身不包含list的均可以不写。会自动映射到。
//            /*@Result(id=true,column="goods_property_id",property="goods_property_id"),
//            @Result(column="goods_id",property="goods_id"),
//            @Result(column="goods_name",property="goods_name"),
//            @Result(column="goods_describe",property="goods_describe"),*/
//
////            @Result(column="goods_id",property="goods_property_infoList",
////                    many=@Many(select="com.emall_3_afternoon.mapper.Goods_Property_InfoMapper.getGoods_Property_List",
////                            fetchType= FetchType.EAGER)),
////            @Result(column = "goods_id", property = "goods_photo_path_infoMapperList",
////                    many = @Many(select="com.emall_3_afternoon.mapper." +
////                            "Goods_Photo_Path_InfoMapper.getGoods_Photo_Path_Info_List",
////                            fetchType= FetchType.EAGER))
//    })
    GoodsInfo getAGoods_Info(int goods_id);
    @Select("SELECT * FROM goods_info")
    List<GoodsInfo> getGoodsList();


}
