package com.sample.wechat;

import org.sword.wechat4j.message.CustomerMsg;
import org.sword.wechat4j.token.TokenProxy;
import org.sword.wechat4j.user.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class WechatServlet
 */
public class WechatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WechatServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyWechat myWechat = new MyWechat(request);
		String result = myWechat.execute();
		response.getOutputStream().write(result.getBytes());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyWechat myWechat = new MyWechat(request);
		String result = myWechat.execute();
		response.getOutputStream().write(result.getBytes());
		getUser(response);
	}

	public void getUser(HttpServletResponse response){
		UserManager userManager = new UserManager();
		//得到openid的列表，此Object是String
		//获取帐号的关注者列表前10000人
		List<Object> userList = userManager.subscriberList();
		System.out.print(userList);
		String accessToken = TokenProxy.accessToken();
	}
	public static void main(String args[]){
		CustomerMsg customerMsg = new CustomerMsg("oTdG6w8e9UGe9fGk460ncF6CRk2c");
		customerMsg.sendText("杨哥厉害了");
	}

}
