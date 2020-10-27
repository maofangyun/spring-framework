package com.mfy.test.web;

import com.mfy.test.bean.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
@SessionAttributes(value={"attr1","attr2"})
public class UserController {

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request){
		System.out.println("测试@InitBinder");
		binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"),false));
	}

    @RequestMapping("/queryUser")
    public @ResponseBody String queryUser() {
        return "jack";
    }

    @RequestMapping(value = "/queryUser",params = "user")
    public @ResponseBody String queryUser(@ModelAttribute("user") User user) {
        return "jack";
    }

	@RequestMapping(value = "/queryUser",params ="date")
	public @ResponseBody String queryUser(Date date) {
		return date.toString();
	}

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("ok");
		mav.addObject("attr1", "attr1Value");
		mav.addObject("attr2", "attr2Value");
		return mav;
	}

	@RequestMapping(value = "/index1")
	public void index1(@ModelAttribute("attr1")String attr1, @ModelAttribute("attr2")String attr2) {
		System.out.println("attr1="+attr1+"  attr2="+attr2);
	}

	/**
	 * 这个方法，所有的请求都会先调用这个方法
	 * */
	@ModelAttribute("user")
	public User initUser(@RequestParam String name) {
		User user = new User(name, "男");
		return user;
	}

	/**
	 *  params： 指定request中必须包含某些参数值是，才让该方法处理。
	 *  headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。
	 *  media type
	 * */
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, params = "username=jack", headers = "Referer=http://www.xx.com/")
	public @ResponseBody String getUser(HttpSession session, OutputStream outputStream) {
		return "xx";
	}

	@RequestMapping(value = "/setUser", method = RequestMethod.GET)
	public @ResponseBody User setUser(@RequestBody User user) {
		return user;
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public @ResponseBody String error() {
		throw new NullPointerException();
	}

	@RequestMapping("/path/{id}/{password}")
	public String pathVariableTest(@PathVariable("id") String username, @PathVariable String password) {
		System.out.println("=======pathVariableTest:username-->" + username + "-->password:" + password);
		return "ok";
	}

}
