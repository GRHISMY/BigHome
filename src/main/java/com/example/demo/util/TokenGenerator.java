package com.example.demo.util;

import org.springframework.stereotype.Component;

/**
 *
 * @Date 2019-10-29 10:34
 * 说明：
 */

@Component
public interface TokenGenerator {

    public String generate(String... strings);

}