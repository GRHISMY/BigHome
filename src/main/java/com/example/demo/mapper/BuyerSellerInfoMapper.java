package com.example.demo.mapper;

import com.example.demo.enpity.BuyerSellerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BuyerSellerInfoMapper {
    @Insert("INSERT INTO `emall_3_afternoon`.`buyer_seller_info`(`b_s_name`, `nickname`, `sex`, `icon`, `telephone`, `pwd`, `home`, `home_detail`, `status`, `b_s_status`, `email`, `last_login_time`, `register_time`) VALUES (#{bSName}, #{nickName}, #{sex}, #{icon}, #{telephone}, #{pwd}, #{home}, #{home_detail}, #{status},#{bSStatus}, #{email}, #{lastLoginTime}, #{registerTime})")
    Integer addUser(BuyerSellerInfo buyerSellerInfo);

    @Select("SELECT * FROM buyer_seller_info")
    List<BuyerSellerInfo> findAll();

    @Select("SELECT * FROM buyer_seller_info WHERE telephone = #{telephone}")
    BuyerSellerInfo finUserByTelephone(String telephone);
}
