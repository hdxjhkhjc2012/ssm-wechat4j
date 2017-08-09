package com.xieke.test.controller;

import com.xieke.test.model.User;
import com.xieke.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sword.wechat4j.message.CustomerMsg;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/all")
	public String helloUser(Model model) {
		List<User> list = userService.findAllUser();
		model.addAttribute("users", list);
		CustomerMsg customerMsg = new CustomerMsg("oTdG6w8e9UGe9fGk460ncF6CRk2c");
		customerMsg.sendText("杨哥厉害了");
		return "/user_list";
	}

	@RequestMapping("/click")
	public String click(Model model) {
		List<User> list = userService.findAllUser();
		model.addAttribute("users", list);
		return "/user_list";
	}
	
}
