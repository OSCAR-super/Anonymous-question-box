package com.lyt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //使用包装器
        XssFilterWrapper xssFilterWrapper=new XssFilterWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(xssFilterWrapper,servletResponse);
    }

    @Override
    public void destroy() {}
}

