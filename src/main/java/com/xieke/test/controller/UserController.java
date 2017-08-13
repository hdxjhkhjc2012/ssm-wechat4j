package com.xieke.test.controller;

import com.xieke.test.model.User;
import com.xieke.test.model.Wuser;
import com.xieke.test.service.UserService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		customerMsg.sendText("浩哥厉害了4444444444");
		return "/index";
	}

	public static String jkUtil(String url, Map map){
		String accessToken = TokenProxy.accessToken();
		String baseUrl = url + "?access_token="+accessToken;
		StringBuffer strURL=new StringBuffer(baseUrl);
		StringBuffer buffer = null;
		System.out.println("通过Map.keySet遍历key和value：");
		for (Object key : map.keySet()) {
			strURL.append("&"+key+"="+ map.get(key));
		}
		URL requrl = null;
		try {
			requrl = new URL(strURL.toString());
			HttpURLConnection httpConn = (HttpURLConnection)requrl.openConnection();
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(),"UTF-8"));
			String line;
			buffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			httpConn.disconnect();
			System.out.println(buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString();
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
		return "/index";
	}

	@RequestMapping("/userAll")
	public String userAll(Model model) {
		UserManager userManager = new UserManager();
		List<Object> userList = userManager.subscriberList();
		List<Wuser> listUser =  new ArrayList<Wuser>();
		for(int i=0 ;i<userList.size(); i++){
			Map map = new HashMap();
			map.put("openid",userList.get(i));
			String a = jkUtil("https://api.weixin.qq.com/cgi-bin/user/info",map);
			JSONObject obj = new JSONObject().fromObject(a);
			Wuser u = (Wuser)JSONObject.toBean(obj,Wuser.class);//将建json对象转换为Person对象
			listUser.add(u);
		}
		model.addAttribute("listUser", listUser);
		return "/User/list";
	}


	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
	@ResponseBody
	public String sendMsg(Model model,String openid ,String content) {
		try{
			CustomerMsg customerMsg = new CustomerMsg(openid);
			customerMsg.sendText(content);
		}catch(Exception e){
			e.printStackTrace();
			return "发送失败";
		}

		return "发送成功";
	}
	
}
