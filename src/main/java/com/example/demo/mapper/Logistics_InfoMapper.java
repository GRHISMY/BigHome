package com.example.demo.mapper;

import com.example.demo.enpity.LogisticsInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Logistics_InfoMapper {
    @Insert("insert into logistics_info(logistics_id, order_id, link_man, link_telephone, goods_address, " +
            "address_1, address_detail, logistics_status) " +
            "values(#{logistics_id}, #{order_id}, #{link_man}, #{link_telephone}, #{goods_address}, " +
            "#{address_1}, #{address_detail}, #{logistics_status})")
    int insertLogistics_Info(LogisticsInfo logistics_info);


    @Select("select * from logistics_info where order_id =#{order_id}")
    List<LogisticsInfo> getLogistics_InfoList(int order_id);
}
