package com.mfy.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;

@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/index")
    public void index() {
        System.out.println(applicationContext.getBean("requestSessionBean"));
    }


    /**
    *  consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
    *  produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
    *  params： 指定request中必须包含某些参数值是，才让该方法处理。
    *  headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。
    *  media type
    * */
    @RequestMapping(value = "/getUser",
            method = RequestMethod.GET,
            params = "username=jack",
            consumes = "application/json",
            produces = "application/json",
            headers = "Referer=http://www.xx.com/")
    public @ResponseBody
    String getUser(HttpSession session, OutputStream outputStream) {
        return "xx";
    }

    /**
        Host                    localhost:8080  
        Accept                  text/html,application/xhtml+xml,application/xml;q=0.9  
        Accept-Language         fr,en-gb;q=0.7,en;q=0.3  
        Accept-Encoding         gzip,deflate  
        Accept-Charset          ISO-8859-1,utf-8;q=0.7,*;q=0.7  
        Keep-Alive              300
    * */
    @RequestMapping("/annotationParamTest")
    public String annotationParamTest(@CookieValue Cookie cookie,
                                      @RequestHeader("Host") String host,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      HttpSession session) {
        return "OK";
    }

    /**
     * 动态url
     * url中带参数，
     * 形如：http://localhost:9090/common/pathVariableTest/jack
     * http://localhost:9090/common/pathVariableTest/james
     * http://localhost:9090/common/pathVariableTest/1
     * http://localhost:9090/common/pathVariableTest/2
     * */
    @RequestMapping("/pathVariableTest/{id}/{password}")
    public @ResponseBody
    String pathVariableTest(@PathVariable("id") String username, @PathVariable String password) {
        System.out.println("=======pathVariableTest:username-->" + username + "-->password:" + password);
        return username + "->" + password;
    }

    /*
    * 形如：http://localhost:9090/common/requestParamTest?id=jack&password=123
    * */
    @RequestMapping(value = "/requestParamTest",method = RequestMethod.GET)
    public @ResponseBody
    String requestParamTest(@RequestParam("id") String username, @RequestParam String password) {
        System.out.println("=======requestParamTest:username-->" + username + "-->password:" + password);
        return username + "->" + password;
    }

    /*
    * 这个方法，所有的请求都会先调用这个方法
    * */
    @ModelAttribute("areaBean")
    public String queryArea(@RequestParam String areaCode) {
        return "first";
    }

}
