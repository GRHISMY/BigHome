package com.example.demo.mapper;

import com.example.demo.enpity.StoreInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StoreInfoMapper {
    @Select("select * from store_info where store_id = #{store_id}")
    StoreInfo getAStoreInfo(Integer store_id);
}
