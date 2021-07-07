package com.cn.exam.resolver;


import com.alibaba.fastjson.JSONObject;
import com.cn.common.utils.RemoteAddrUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import static com.cn.exam.constant.ConfigConstant.REQUEST_ID;
import static com.cn.exam.constant.ConfigConstant.REQUEST_ID_PREFIX;

/**
 * 转换封装json文本成为JsonObject对象，方便后面使用
 * Created by yml on 2016/11/1.
 */
@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "RequestWrapperFilter")
public class RequestWrapperFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest resquest = (HttpServletRequest) servletRequest;
        String requestID = REQUEST_ID_PREFIX + System.currentTimeMillis();
        resquest.setAttribute(REQUEST_ID, requestID);
        MDC.put(REQUEST_ID, requestID);
        log.info("------------------------------------------------------------");
        log.info("                                                            ");
        log.info(" ## request method -> {}", resquest.getMethod());
        log.info(" ## request uri -> {}", resquest.getRequestURI());
        log.info(" ## request ip -> {}", RemoteAddrUtils.getIpAddr(resquest));
        try {
            if (servletRequest.getContentType() != null
                    && (servletRequest.getContentType().toLowerCase().contains("json")
//                       ||
//                       servletRequest.getContentType().toLowerCase().contains("xml")
            )) {
                RequestBodyWrapper myRequestWrapper = new RequestBodyWrapper((HttpServletRequest) servletRequest);
                log.info(" ## request params -> {}", myRequestWrapper.getJsonText());
                log.info("                                                            ");
                log.info("------------------------------------------------------------");
                filterChain.doFilter(myRequestWrapper, servletResponse);
            } else {
                log.info(" ## request params -> {}", JSONObject.toJSONString(resquest.getParameterMap()));
                log.info("                                                            ");
                log.info("------------------------------------------------------------");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
