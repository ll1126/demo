package com.test.demo.modular.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.core.auth.ATTUser;
import com.test.demo.modular.sys.entity.ManagerUser;
import com.test.demo.modular.sys.mapper.ManagerRoleMapper;
import com.test.demo.modular.sys.mapper.ManagerUserMapper;
import com.test.demo.modular.sys.service.LoginService;
import com.test.demo.util.DateUtil;
import com.test.demo.util.JsonResult;
import com.test.demo.util.JwtHelper;
import com.test.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ManagerUserMapper managerUserMapper;
    @Autowired
    private ManagerRoleMapper managerRoleMapper;

    @Value("${base64Security}")
    private String base64Security;

    /**
     * 登陆
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public JsonResult login(String userName, String password) {
        //根据登陆用户名查询是否存在该用户
        if (userName == null || userName.trim().isEmpty()) {
            return new JsonResult(1, null, "登陆用户名不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            return new JsonResult(1, null, "密码不能为空");
        }
        ManagerUser managerUser = managerUserMapper.selUserBymanagerName(userName);
        if (managerUser == null) {
            return new JsonResult(1, null, "没有该用户");
        }
        if (!MD5Util.getMD5(password).equals(managerUser.getManagerPassword())) {
            return new JsonResult(1, null, "密码错误");
        }
        if (managerUser.getState() == 1) {
            return new JsonResult(1, null, "该账号已经禁用，请联系管理员");
        }
        //该用户对应的角色id
        Integer roleId = managerRoleMapper.selRoleByUserId(managerUser.getId());
        JSONObject tokenJson = new JSONObject();
        //生成token并返回
        String token = JwtHelper.createJWT(String.valueOf(managerUser.getId()), String.valueOf(roleId), ATTUser.Out_Hour_Pc * 60 * 60 * 1000, base64Security);
        tokenJson.put("token", token);
        tokenJson.put("expDate", DateUtil.addHourByDay(new Date(), ATTUser.Out_Hour_Pc));
        return new JsonResult(0, token, "登陆成功，噢耶( •̀ ω •́ )y");
    }
}
