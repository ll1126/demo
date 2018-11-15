package com.test.demo.modular.sys.controller;

import com.test.demo.core.redis.RedisService;
import com.test.demo.core.redis.key.UserKey;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 用thymeleaf 模板的话 别用@RestController 需要返回json数据的话 在方法上单独用@ResponseBody
//@RestController
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Autowired
    RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;


    @RequestMapping("/api/index")
    public String userList2(Model model) {
        // model 可以传递数据去页面
        model.addAttribute("hello", "Hi, Spring Boot!");
        return "/Hello";  //返回模板文件的名字 jar包运行报错的话 去掉前面的 /
    }

    /**
     * 页面缓存
     *
     * @param model
     * @return
     */
    @RequestMapping("/api/index2")
    @ResponseBody
    public String userList2(HttpServletRequest request, HttpServletResponse response, Model model) {
        // model 可以传递数据去页面
        model.addAttribute("hello", "Hi, Spring Boot!!!!!");

        //去缓存
        String html = redisService.get(UserKey.getById, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        WebContext ctx = new WebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("Hello", ctx);
        if (!StringUtils.isEmpty(html)) {
            redisService.set(UserKey.getById, "", html);
        }
        return html;
    }

}
