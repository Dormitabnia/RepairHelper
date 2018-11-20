package com.ss.rh.interceptor;

import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.entity.Authentication;
import com.ss.rh.service.AuthenticationService;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法则直接通过
        if (!(handler instanceof HandlerMethod))
            return true;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        // 不是LoginRequired标注的方法直接通过
        if (methodAnnotation == null)
            return true;

        String token = request.getHeader("token");
        if (token == null) {
            pw.write(JsonUtil.failure("无token，请先登录", 401));
        }
        else {
            //进行token验证
            Authentication auth = authenticationService.getUserIdByAccessToken(token);
            if (auth == null)
                pw.write(JsonUtil.failure("找不到该用户"));
            else {
                request.setAttribute("userId", auth.getUserId());

                return true;
            }
        }

        pw.flush();
        pw.close();

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
