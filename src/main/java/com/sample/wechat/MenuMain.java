package com.sample.wechat;

import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import org.sword.wechat4j.token.TokenProxy;

/**
 * Created by wx on 2017/8/11.
 */
public class MenuMain {

    public static void main(String[] args) {

        ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");


        ViewButton vbt=new ViewButton();
        vbt.setUrl("http://www.baidu.com");
        vbt.setName("嗖嗖嗖");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);


        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
        button.add(cbt);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);
        String accessToken = TokenProxy.accessToken();
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;

        try{
            String rs=HttpUtils.sendPostBuffer(url, menujson.toJSONString());
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }

    }

}