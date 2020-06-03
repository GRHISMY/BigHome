package com.example.demo.mapper;

import com.example.demo.enpity.OrderInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderInfoMapper {
    @Select("select * from order_info where store_id = #{b_s_id}")
    List<OrderInfo> getOrder_InfoList(int b_s_id);

    @Insert("insert into order_info(order_id, order_no, order_money, order_time, store_id, " +
            "order_status, pay_way, pay_status, pay_time) " +
            "values(#{order_id}, #{order_no}, #{order_money}, #{order_time}, #{store_id}, " +
            "#{order_status}, #{pay_way}, #{pay_status}, #{pay_time})")
    @Options(useGeneratedKeys=true,keyProperty="order_id",keyColumn="order_id")
    int insertOrder_Info(OrderInfo order_info);
}
