package com.kele.intercept;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 12402
 */
public class UserStatusIntercept implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /* 1.如果该请求是用来登录或是注册的则直接放行
         * 2.判断session是否存在用户对象
         */
        String url = request.getRequestURI().toString();
        if (url.contains("login") || url.contains("regist")){
            return true;
        }
        Object user = request.getSession().getAttribute("user");
        if (user != null){
            return true;
        }

        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
