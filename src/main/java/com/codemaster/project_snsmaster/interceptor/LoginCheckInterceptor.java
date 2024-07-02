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

        String path = request.getServletPath();

        String query = request.getQueryString(); //파라미터가 있는지 없는지

        HttpSession session = request.getSession();
        Object getSession = session.getAttribute("userid");
        if (getSession != null) {
            session.removeAttribute("prev_url");
        } else if (getSession == null) {// 로그인 안한 사람

            response.sendRedirect(request.getContextPath() + "/loginForm");

            if (query != null) {

                session.setAttribute("prev_url", path + "?" + query);

            } else if (query == null) {

                session.setAttribute("prev_url", path);

            }
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
