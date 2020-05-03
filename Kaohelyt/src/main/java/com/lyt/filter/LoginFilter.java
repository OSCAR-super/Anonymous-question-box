package com.lyt.filter;






import com.lyt.controller.UserController;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        if (request.getRequestURI().contains("/UserLogin.action")){
            String L=(String) request.getSession().getAttribute("autoL");
            if (L=="yes"){
                Cookie[] cookies=request.getCookies();
                Cookie ck=null;
                for (Cookie c:cookies){
                    if (c.getName().equals("autoLogin")){
                        ck=c;
                    }
                }
                if (ck!=null){
                    String UserLoginName=ck.getValue().split("-")[0];
                    if (UserLoginName!=null){
                        request.getRequestDispatcher("/AllUser.action").forward(request,response);
                    }
                }
            }

        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
