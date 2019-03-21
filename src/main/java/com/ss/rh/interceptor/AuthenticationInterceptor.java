package com.ss.rh.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.constants.ConfigProperties;
import com.ss.rh.constants.Constants;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.RedisCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Autowired
    ConfigProperties configProperties;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法则直接通过
        if (!(handler instanceof HandlerMethod))
            return true;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        // 不是LoginRequired标注的方法直接通过
        if (methodAnnotation == null)
            return true;

        response.reset();
//        PrintWriter pw = response.getWriter();

        // 在header和attribute中查找token
        String token = request.getHeader(configProperties.getWX_TOKEN_NAME());

        logger.info("method:" + method.getName());
        logger.info("token:" + token);

        if (token == null) {
            token = (String) request.getAttribute(configProperties.getWX_TOKEN_NAME());
            if(token == null) {
                PrintWriter pw = response.getWriter();
                pw.write(JsonUtil.failure("找不到token"));
                pw.flush();
                pw.close();
            }
        }
        else {
            // 在redis中验证该token是否登录
            if (redisCacheUtil.existsKey(token)) {
                // 验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constants.TOKEN_SECRET)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
                    // 验证发生异常
                    PrintWriter pw = response.getWriter();
                    pw.write(JsonUtil.failure("token验证不通过！"));
                    pw.flush();
                    pw.close();
                    return false;
                }
                return true;
            }
            else {
                PrintWriter pw = response.getWriter();
                pw.write(JsonUtil.failure("用户尚未登录", 401));
                pw.flush();
                pw.close();
            }
        }

//        pw.flush();
//        pw.close();

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
