package com.example.boottest.config.interceptors;

import com.example.boottest.util.TokenHelper;
import com.example.boottest.util.TokenInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");

        // test code start here
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession();
        //AuthorizingRealm



        // test code end here
        // "CAuth 6n-RoCVZSKANYK4TJ8cuV6Snp6fJmw.."
        if(authorization != null && !authorization.isEmpty()) {
                TokenInfo tokenInfo = TokenHelper.parseToken(authorization);
                if (tokenInfo == null) {
                    // return 401
                } else {
                    request.setAttribute("tokenInfo", tokenInfo);
                }
            }

        return true;
    }
}
