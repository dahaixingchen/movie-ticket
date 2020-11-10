package com.fei.search.ticket.config;

import com.fei.search.ticket.bo.QueryConditionBo;
import com.fei.search.ticket.util.ApiMessage;
import com.fei.search.ticket.util.MessageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName: LoginConfigurerInterceptor
 * @Author chengfei
 * @Date 2020/11/10 15:18
 * @Description: TODO
 **/
//@Component
public class LoginConfigurerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LoginConfigurerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException {

//        request.setAttribute("check",ApiMessage.error("对不起，您不是VIP，请联系服务员小姐姐呦"));
        return true;
    }

    /**
     * @Description: 拦截中需要中的事情
      * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @return void
     * @date: 2020/11/10 15:59
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) {
//        logger.info("拦截到查询的页面，请处理");
    }

    /**
     * @Description:  拦截后需要做的事情
      * @param request
     * @param response
     * @param handler
     * @param ex
     * @return void
     * @date: 2020/11/10 15:59
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        logger.info("afterCompletion method is now running!");
    }
}
