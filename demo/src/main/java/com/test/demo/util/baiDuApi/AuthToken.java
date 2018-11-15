package com.test.demo.util.baiDuApi;


import com.alibaba.fastjson.JSONObject;
import com.test.demo.core.constant.Const;
import com.test.demo.util.HttpUtil;

import java.util.Date;

/**
 * 获取token类
 *
 * @Author : WuShukai
 * @Date :2018/2/12 10:04
 */

public class AuthToken {

    /**
     * 百度API获取 access_token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取
     *
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth() {
        String authTokenUrl = Const.BAIDU_authToken
                + "?grant_type=client_credentials&client_id="
                + Const.BAIDU_API_Key + "&client_secret="
                + Const.BAIDU_Secret_Key;
        try {
            JSONObject jsonObject = HttpUtil.doGet(authTokenUrl);
            System.out.println("result:" + jsonObject);
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            System.err.printf("BAIDU AuthToken 获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static void main(String[] args) {
        long time = new Date().getTime();
        String token = getAuth();
        long time2 = new Date().getTime();
        System.out.println(time2 - time);
        System.out.println(token);
    }

}