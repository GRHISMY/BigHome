package com.example.demo.mapper;

import com.example.demo.enpity.AddressInfo;
import com.example.demo.provider.addressInfoProvider.updateProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressInfoMapper {
    @Update("UPDATE address_info SET default_status = 1 WHERE b_s_id = #{b_s_id}")
    Integer editDefaultStatusFirst(Integer b_s_id);

    @Update("UPDATE address_info SET default_status = 0 WHERE address_id = #{address_id}")
    Integer editDefaultStatusSend(Integer address_id);

    @Select("SELECT * FROM address_info WHERE b_s_id = #{b_s_id}")
    List<AddressInfo> findAllAddress(Integer b_s_id);

    @Insert("INSERT INTO `address_info`(`b_s_id`, `address`, `address_detail`, `addresssee`, `telephone`, `default_status`, `postcode`) VALUES ( #{b_s_id}, #{address}, #{address_detail}, #{addresssee}, #{telephone}, #{default_status}, #{postcode})")
    Integer insertAddress(AddressInfo addressInfo);

    @Delete("DELETE FROM address_info WHERE address_id=#{address_id}")
    Integer deleteAddress(Integer address_id);

    @UpdateProvider(type = updateProvider.class,method = "updteAddressInfo")
    Integer updataAddressIonfo(AddressInfo addressInfo);

    @Select("SELECT address_id FROM address_info WHERE b_s_id = #{b_s_id} AND default_status = 0")
    Integer findDefaultStatus(Integer b_s_id);

    @Select("SELECT * FROM  address_info WHERE address_id = #{address_id}")
    AddressInfo findOne(Integer address_id);
}
