package com.test.demo.core.auth;

import com.alibaba.fastjson.JSONObject;

import com.test.demo.util.JsonResult;
import com.test.demo.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

public class AuthInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String base64Security;

    public AuthInterceptor(String base64Security) {
        this.base64Security = base64Security;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        HttpServletRequest req = (HttpServletRequest) request;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        HttpServletResponse resp = (HttpServletResponse) response;
        //这里填写你允许进行跨域的主机ip
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization");

        //排除不需要验证token的
//        if(req.getRequestURI().contains("/api/home/login") || req.getRequestURI().contains("/api/pc") || req.getRequestURI().contains("AcceptReport")){
//
//
//            return true;
//        }
        String token = req.getHeader("Authorization");
        String url = req.getRequestURI();
        logger.warn(url);

        JsonResult res = new JsonResult();
        Claims c = null;
        Integer userId = null;
        Integer roleId = null;
        if (null == token || token.isEmpty()) {
            res.setCode(403);
            res.setMessage("token没有认证通过!原因为：客户端请求参数中无token信息");
            res.setContent(null);
        } else {
            //验证Token
            c = JwtHelper.parseJWT(token, base64Security);
            if (c == null) {
                res.setCode(403);
                res.setMessage("token没有认证通过!原因为token不存在或者已过期");
            } else {
                JSONObject jsonObject = JwtHelper.getUserMessage(token, base64Security);
                userId = jsonObject.getInteger("userId");
                roleId = jsonObject.getInteger("roleId");
                if (userId != null && userId != 0) {
                    request.setAttribute(ATTUser.USER_TOKEN, userId);
                } else {
                    res.setCode(403);
                    res.setMessage("token没有认证通过!原因为：客户端请求中认证的token信息无效，请查看申请流程中的正确token信息");
                    res.setContent(null);
                }
            }
        }
        if (res.getCode() == 403) {
            HttpServletResponse hsp = (HttpServletResponse) response;
            hsp.setHeader("content-type", "text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String resJSON = JSONObject.toJSONString(res);
            out.print(resJSON);
            return false;
        }
        Long hour = (c.getExpiration().getTime() - (new Date()).getTime()) / 1000 / 60 / 60;
        //时间小于24小时再生成一个新的token
        if (hour <= 24) {
            String refreshToken = JwtHelper.createJWT(userId.toString(), String.valueOf(roleId), ATTUser.Out_Hour_Pc * 60 * 60 * 1000, base64Security);
            response.setHeader("Authorization", refreshToken);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
