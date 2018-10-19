package com.test.demo.core.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import com.test.demo.core.auth.ATTUser;
import com.test.demo.util.JsonResult;
import com.test.demo.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * 切面类
 */
@Component
@Aspect
public class WebControllerAop {

    @Value("${base64Security}")
    public String base64Security;

    /**
     * 制定切点
     * 匹配com.test.demo.controller包及其子包下的所有类的所有方法
     */
    @Pointcut("execution(* com.test.demo.modular.sys.controller..*.*(..))")
    public void executeService() {

    }

    /**
     * 前置通知，方法调用前被调用
     *
     * @param joinPoint 可以获得通知的签名信息，如目标方法名、目标方法参数信息等
     *                  HttpServletRequest  来获取请求信息，Session信息
     */
//    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("我是前置通知!!!");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        System.out.println(signature.getName());
        //AOP代理类的名字
        System.out.println(signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String, String> parameterMap = Maps.newHashMap();
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter, request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if (obj.length > 0) {
            System.out.println("请求的参数信息为：" + str);
        }

    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     *
     * @param joinPoint
     * @param keys
     */
//    @AfterReturning(value = "execution(* com.test.demo.modular.sys.controller..*.*(..))", returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {

        System.out.println("第一个后置返回通知的返回值：" + keys);
    }

//    @AfterReturning(value = "execution(* com.test.demo.modular.sys.controller..*.*(..))", returning = "keys", argNames = "keys")
    public void doAfterReturningAdvice2(String keys) {

        System.out.println("第二个后置返回通知的返回值：" + keys);
    }

    /**
     * 后置异常通知
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     *
     * @param joinPoint
     * @param exception
     */
//    @AfterThrowing(value = "executeService()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        //目标方法名：
        System.out.println(joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException) {
            System.out.println("发生了空指针异常!!!!!");
        }
    }

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("execution(* com.test.demo.modular.sys.controller..*.*(..))")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
//        try {//obj之前可以写目标方法执行前的逻辑
//            Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
//            return obj;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;

        /**   AOP中配置跨域   */
        //获取response
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //核心设置
        //这里填写你允许进行跨域的主机ip
        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, authorization");

        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);


        /**    不带/api 的需要验证token    */
        if (!request.getRequestURI().contains("/api")) {
            JsonResult jsonResult = verificationToken(request, response);
            //返回的code是403 没通过，直接返回
            if (jsonResult.getCode() == 403) {
                return jsonResult;
            }
        }

        //执行调用的方法
        long startTime = new Date().getTime();
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("请求：【" + request.getRequestURI() + "】用时: " + (new Date().getTime() - startTime));
        return proceed;

    }

    /**
     * 验证token
     *
     * @return
     */
    public JsonResult verificationToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //验证token
        String token = request.getHeader("Authorization");
        JsonResult res = new JsonResult();
        Claims c = null;
        Integer userId = null;
        Integer roleId = null;
        String userName = null;
        boolean isFilter = false;
        if (null == token || token.isEmpty()) {
            res.setCode(403);
            res.setMessage("token没有认证通过!原因为：客户端请求参数中无token信息");
        } else {
            //验证Token
            c = JwtHelper.parseJWT(token, base64Security);
            if (c == null) {
                res.setCode(403);
                res.setMessage("token没有认证通过!原因为：token不存在或者已过期");
            } else {
                JSONObject jsonObject = JwtHelper.getUserMessage(token, base64Security);
                userId = jsonObject.getInteger("userId");
                roleId = jsonObject.getInteger("roleId");
                if (userId != null && userId != 0) {
                    request.setAttribute(ATTUser.USER_TOKEN, userId);
                    request.setAttribute(ATTUser.ROLE_TOKEN, roleId);
                    Long hour = (c.getExpiration().getTime() - (new Date()).getTime()) / 1000 / 60 / 60;
                    //更新token
                    if (hour <= 24) {
                        String refreshToken = JwtHelper.createJWT(userId.toString(), String.valueOf(roleId), ATTUser.Out_Hour_Pc * 60 * 60 * 1000, base64Security);
                        response.setHeader("Authorization", refreshToken);
                    }
                } else {
                    res.setCode(403);
                    res.setMessage("token没有认证通过!原因为：客户端请求中认证的token信息无效，请查看申请流程中的正确token信息");
                }
            }
        }
        return res;
    }
}
