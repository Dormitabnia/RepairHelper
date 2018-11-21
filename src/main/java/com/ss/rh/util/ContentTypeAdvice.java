package com.ss.rh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * ContentTypeAdvice
 *
 * edit response body
 */
@ControllerAdvice
public class ContentTypeAdvice implements ResponseBodyAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ContentTypeAdvice.class);

    // Whether this component supports the given controller method return type and the selected HttpMessageConverter type.
    // ?
    // return true 表示对任何handler的responsebody都调用beforeBodyWrite()
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // Content-Type: application/json;charset=UTF-8
        serverHttpResponse.getHeaders().setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        return o;
    }
}
