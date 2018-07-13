package com.example.boottest.config.interceptors;

import com.example.boottest.config.Authorize;
import com.example.boottest.util.TokenInfo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception{
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            // return with some message
            return false;
        }

        Authorize auth = ((HandlerMethod)handler).getMethod().getAnnotation(Authorize.class);
        // 无需授权
        if(null == auth){
            return true;
        }

        Object tokenObj = request.getAttribute("tokenInfo");
        TokenInfo tokenInfo;
        if(tokenObj instanceof TokenInfo){
            tokenInfo = (TokenInfo)tokenObj;
        } else {
            // 需要授权,但无登录信息
            // return with some message
            return false;
        }

        String respectRole = auth.role();
        if(!respectRole.equals(tokenInfo.getId() + "")){
            // return with some message (401)
            return false;
        }

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }
}
