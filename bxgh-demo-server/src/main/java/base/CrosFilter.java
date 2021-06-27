package edu.jd.xyt.base;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") //"/*"表示所有请求都会被本过滤器拦截
public class CrosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req =(HttpServletRequest) servletRequest;

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getServletPath();

        System.out.println("path:"+path);

        //跨域设置
        resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
        resp.setHeader("Access-Control-Allow-Headers","Origin,X-Requested-With,Content-Type, Accept, Token,Authorization");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setHeader("Access-Control-Allow-Credentials","true");


        filterChain.doFilter(servletRequest,servletResponse);//通过放行
    }

    @Override
    public void destroy() {

    }
}
