package com.cn.exam.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.cn.common.utils.MyString;
import com.cn.exam.annotation.PassToken;
import com.cn.exam.base.pmJwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 *@Author fengzhilong
 *@Date 2020/12/22 18:26
 *@Desc
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 如果不是映射到方法，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //检查是否有跳过检查的注解 @PassToken
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //从http请求头中取出token
        String token = request.getHeader("token");
        if (MyString.isEmpty(token)) {
            setHttpResponse(response, "无token,请重新登录");
            log.error("无token,请重新登录");
            return false;
        }

        //获取token中的用户信息
        try {
            String ghid = JWT.decode(token).getAudience().get(0);
        } catch (Exception e) {
            setHttpResponse(response, "token失效或异常，请重新登录");
            log.error("token失效或异常，请重新登录");
            return false;
        }

        // 验证 token的合法性
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(pmJwtToken.jwtTokenSecret)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            setHttpResponse(response, "token失效或非法，请重新登录");
            log.error("token失效或非法，请重新登录");
            return false;
        }

        return true;
    }


    /**
     * 回写响应数据
     * @param response
     * @param strTokenDes
     */
    private void setHttpResponse(HttpServletResponse response, String strTokenDes) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();

            HashMap htRet = new HashMap<>();
            htRet.put("ret", "ERROR_TOKEN");
            htRet.put("data", strTokenDes);
            htRet.put("cmd", "token_verification");
            String strError = JSONObject.toJSONString(htRet);

            out.print(strError);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("response返回数据异常");
        }

    }
}
