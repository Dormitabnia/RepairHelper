package com.ss.rh.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.ss.rh.annotation.BLoginRequired;
import com.ss.rh.constants.ConfigProperties;
import com.ss.rh.constants.Constants;
import com.ss.rh.util.JsonUtil;
import com.ss.rh.util.RedisCacheUtil;
import com.ss.rh.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class BackendAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法则直接通过
        if (!(handler instanceof HandlerMethod))
            return true;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        BLoginRequired blgAnnotation = method.getAnnotation(BLoginRequired.class);

        // 不是LoginRequired标注的方法直接通过
        if (blgAnnotation == null)
            return true;

        response.reset();

        String token = request.getHeader(configProperties.getBK_TOKEN_NAME());

        if (token == null) {
            PrintWriter pw = response.getWriter();
            pw.write(JsonUtil.failure("找不到token"));
            pw.flush();
            pw.close();
            return false;
        }

        if (!redisCacheUtil.existsKey(token)) {
            PrintWriter pw = response.getWriter();
            pw.write(JsonUtil.failure("尚未登录或token已过期", 401));
            pw.flush();
            pw.close();
            return false;
        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constants.B_TOKEN_SECRET)).build();

        boolean isValid = tokenUtil.verify(jwtVerifier, token);

        if (!isValid) {
            PrintWriter pw = response.getWriter();
            pw.write(JsonUtil.failure("token验证不通过"));
            pw.flush();
            pw.close();
            return false;
        }

        return true;
    }
}
