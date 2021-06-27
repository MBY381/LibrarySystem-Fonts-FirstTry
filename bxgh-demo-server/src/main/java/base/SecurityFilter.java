package edu.jd.xyt.base;

import edu.jd.xyt.common.CurrentUser;
import org.apache.ibatis.jdbc.Null;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class SecurityFilter implements Filter {

    private static final String[]  freePaths={"login.html","login"};
    private static final String[]  freeDirs={"/css","/fonts","/images","/js","/layer"};

    private static boolean isFrePath(String thePath){
            for(String path:freePaths){
                if(thePath.equals(path)){
                    return true;
                }
            }
            return false;
    }
    private static boolean hasPrefix(String thePath){
        for(String path:freeDirs){
            if(thePath.startsWith(path)){
                return true;
            }
        }
        return false;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        resp.setHeader("Cache-Control", "no-cache,no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("expires", -1);

        String path = req.getServletPath();

        if(hasPrefix(path)||isFrePath(path)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String method=req.getMethod();
        if(method.equalsIgnoreCase("options")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        HttpSession session=req.getSession();
        if(session.getAttribute(CurrentUser.SESSION_ATTR_NAME)!=null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
