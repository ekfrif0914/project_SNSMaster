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

        System.out.println(path+"path1");

        String query = request.getQueryString(); //파라미터가 있는지 없는지

        System.out.println(query + "query");


        HttpSession session = request.getSession();
        Object getSession = session.getAttribute("userid");
        Object getSession2 = session.getAttribute("managerid");// 세션에서 값을 가져온다.
        Object prev_url = session.getAttribute("prev_url");
        if(getSession!=null ) {
            session.removeAttribute("prev_url");
        }


       else if(getSession == null) {// 로그인 안한 사람
            response.sendRedirect(request.getContextPath() + "/loginForm");

            if (query != null) { //주소가 board?no=34&page=33

                session.setAttribute("prev_url", path + "?" + query);
                System.out.println(session.getAttribute("prev_url") + "path");
                //return true;
            } else if (query == null) { //board

                session.setAttribute("prev_url", path);
                System.out.println(session.getAttribute("prev_url") + "1");
                // return true;
            }
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
