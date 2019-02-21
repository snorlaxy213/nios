package com.springboot.component;

import com.springboot.dto.Message;
import com.springboot.exception.GlobalException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)

    public Message exceptionHandler(HttpServletRequest request,Exception e){
        e.printStackTrace();
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException)e;
            return Message.fail(globalException.getMsg());
        }else if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException  = (MethodArgumentNotValidException)e;
            String errorMsg = methodArgumentNotValidException.getMessage();
            return Message.fail(errorMsg);
        }else {
            return Message.fail(e.getMessage());
        }
    }

}
