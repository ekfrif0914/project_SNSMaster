package com.codemaster.project_snsmaster.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object getSession = session.getAttribute("userid");
        Object getSession2 = session.getAttribute("managerid");// 세션에서 값을 가져온다.
        if (getSession == null) {// 로그인 안한 사람
            response.sendRedirect(request.getContextPath() + "/loginForm");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
