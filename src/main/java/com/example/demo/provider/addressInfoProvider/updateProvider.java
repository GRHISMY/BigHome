package com.example.demo.provider.addressInfoProvider;

import com.example.demo.enpity.AddressInfo;
import org.apache.ibatis.jdbc.SQL;

public class updateProvider {
    public String updteAddressInfo(final AddressInfo addressInfo){
        return new SQL(){{
            UPDATE("address_info");
            if (addressInfo.getB_s_id() != null){
                SET("b_s_id=#{b_s_id}");
            }
            if (addressInfo.getAddress() != null){
                SET("address=#{address}");
            }
            if (addressInfo.getAddress_detail() != null){
                SET("address_detail=#{address_detail}");
            }
            if (addressInfo.getAddresssee() != null){
                SET("addresssee=#{addresssee}");
            }
            if (addressInfo.getTelephone() != null){
                SET("telephone=#{telephone}");
            }
            if (addressInfo.getDefault_status() != null){
                SET("default_status=#{default_status}");
            }
            if (addressInfo.getPostcode() != null){
                SET("postcode=#{postcode}");
            }
            WHERE("address_id =#{address_id}" );
        }}.toString();
    }
}
