package com.example.demo.exception;


import com.example.demo.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class CommonExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public JsonData Handler(Exception e) {
//        if (e instanceof CommonException) {
//            CommonException commonException = (CommonException) e;
//            return JsonData.buildError(commonException.getMessage(), commonException.getCode());
//        } else {
//            return JsonData.buildError("未知错误！");
//        }
//
//    }
}
