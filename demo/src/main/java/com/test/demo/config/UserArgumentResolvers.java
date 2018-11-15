package com.test.demo.config;


import com.alibaba.fastjson.JSONObject;
import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.mapper.ManagerUserMapper;
import com.test.demo.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserArgumentResolvers implements HandlerMethodArgumentResolver {

    @Value("${base64Security}")
    public String base64Security;

    @Autowired
    ManagerUserMapper managerUserMapper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        // 参数类型是 ManagerUser 才进行下面的方法 resolveArgument
        return clazz== ManagerUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // 从token里取出userId 并查找到信息返回
        String token = request.getHeader("Authorization");
        JSONObject jsonObject = JwtHelper.getUserMessage(token, base64Security);
        String userId = jsonObject.getString("userId");
        return managerUserMapper.selUserBymanagerId(userId);
    }
}
