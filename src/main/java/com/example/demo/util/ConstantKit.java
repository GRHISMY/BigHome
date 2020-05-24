package com.example.demo.util;

/**
 *
 * @Date 2019-10-29 10:34
 * 说明：
 */
public final class ConstantKit {

    /**
     * 设置删除标志为真
     */
    public static final Integer DEL_FLAG_TRUE = 1;

    /**
     * 设置删除标志为假
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    /**
     * redis存储token设置的过期时间，30分钟
     */
    public static final Integer TOKEN_EXPIRE_TIME = 60 * 30;

    /**
     * 设置可以重置token过期时间的时间界限
     */
    public static final Integer TOKEN_RESET_TIME = 1000 * 100;


}