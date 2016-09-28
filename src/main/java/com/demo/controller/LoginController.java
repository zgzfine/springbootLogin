package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.demo.service.UserService;
import com.demo.util.JsonResult;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",produces="application/json")
	@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(JsonResult.FALSE);
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if(userService.findRepeat(account,password)){
			jsonResult.setResult(JsonResult.SUCCESS);
			jsonResult.setMessage("登入");
		}else
			jsonResult.setMessage("请先注册");
		
		return JSON.toJSON(jsonResult).toString();
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";   
	}
}
