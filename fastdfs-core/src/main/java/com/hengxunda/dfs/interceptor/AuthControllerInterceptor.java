package com.hengxunda.dfs.interceptor;

import com.hengxunda.dfs.base.BaseConntroller;
import com.hengxunda.dfs.base.ErrorCode;
import com.hengxunda.dfs.base.spring.SpringContext;
import com.hengxunda.dfs.core.service.AppInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthControllerInterceptor extends BaseConntroller implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthControllerInterceptor.class);

    private AppInfoService appInfoService = SpringContext.getBean(AppInfoService.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("preHandle");
        }
        String appKey = request.getHeader(HEADER_APP_KEY);
        String timestamp = request.getHeader(HEADER_TIMESTAMP);
        String sign = request.getHeader(HEADER_SIGN);
        response.setContentType(UTF8_JSON);
        if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(sign)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().write(getResponseByCode(ErrorCode.AUTH_PARAM_ERROR).getBytes(UTF8));
            response.getOutputStream().flush();
            return false;
        }
        ErrorCode eCode = appInfoService.checkAuth(appKey, timestamp, sign);
        if (eCode != ErrorCode.OK) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().write(getResponseByCode(eCode).getBytes(UTF8));
            response.getOutputStream().flush();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        if (logger.isDebugEnabled()) {
            logger.debug("postHandle");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
