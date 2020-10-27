package com.mfy.test.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.mfy.test.web")
public class GlobalExceptionHandler {

    @ExceptionHandler({ArrayIndexOutOfBoundsException.class})
    public @ResponseBody
    String handlerArrayIndexOutOfBoundsException(Exception e) {
        e.printStackTrace();
        return "ArrayIndexOutOfBoundsException";
    }

    @ExceptionHandler({NullPointerException.class})
    public @ResponseBody
    String handlerNullPointerException(Exception e, Throwable t) {
        e.printStackTrace();
        if(t == null){
			System.out.println("cause为空");
		}
        return "NullPointerException";
    }
}
