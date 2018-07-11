package com.example.boottest.config.interceptors;

import com.example.boottest.config.Auth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor extends HandlerInterceptorAdapter {
    private long startTime, endTime;
    public void setStartTime(long startTime){
        this.startTime = startTime;
    }

    public void setEndTime(long endTime){
        this.endTime = endTime;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception{
        System.out.println("MyInterceptor prehandler");
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            System.out.println("cat cast handler to Handlermethod.class");
            return true;
        }

        Auth auth = ((HandlerMethod)handler).getMethod().getAnnotation(Auth.class);
        if(null == auth){
            System.out.println("can't find @Auth in this url:" + request.getRequestURI());
            return true;
        }

        String admin = auth.user();
        if(!admin.equals(request.getAttribute("role"))){
            System.out.println("permission denied");
            response.setStatus(403);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("执行postHandle方法-->02");
        super.postHandle(request, response, handler, modelAndView);
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                ModelAndView modelAndView, Exception ex) throws Exception {
        System.out.println("执行afterCompletion方法-->03");
        super.afterCompletion(request, response, handler, ex);

    }

}
