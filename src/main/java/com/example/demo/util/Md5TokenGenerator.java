package com.example.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 *
 * @Date 2019-10-29 10:57
 * 说明：
 */

@Component
public class Md5TokenGenerator implements TokenGenerator {

    @Override
    public String generate(String... strings) {
        long timestamp = System.currentTimeMillis();
        String tokenMeta = "";
        for (String s : strings) {
            tokenMeta = tokenMeta + s;
        }
        tokenMeta = tokenMeta + timestamp;
        String token = DigestUtils.md5DigestAsHex(tokenMeta.getBytes());
        return token;
    }
}