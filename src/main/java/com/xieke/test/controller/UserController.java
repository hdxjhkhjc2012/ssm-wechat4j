package com.xieke.test.controller;

import com.xieke.test.model.User;
import com.xieke.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sword.wechat4j.message.CustomerMsg;
import org.sword.wechat4j.token.TokenProxy;
import org.sword.wechat4j.user.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
		customerMsg.sendText("浩哥厉害了");
		return "/user/user_list";
	}

	@RequestMapping(value = "/click", method = RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public String click( Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String accessToken = TokenProxy.accessToken();
		String strURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid=oTdG6w8e9UGe9fGk460ncF6CRk2c&lang=zh_CN";
		URL url = new URL(strURL);
		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.connect();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				httpConn.getInputStream(),"UTF-8"));
		String line;
		StringBuffer buffer = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		httpConn.disconnect();

		System.out.println(buffer.toString());

		List<User> list = userService.findAllUser();
		UserManager userManager = new UserManager();
		List<Object> userList = userManager.subscriberList();
		System.out.print(userList);
		model.addAttribute("users", list);
		return "/user/user_list";
	}
	
}
