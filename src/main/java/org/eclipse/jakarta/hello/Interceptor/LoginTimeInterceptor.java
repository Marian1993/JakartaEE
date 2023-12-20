package org.eclipse.jakarta.hello.Interceptor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(servletNames = {"Photos","PhotosForm"})
public class LoginTimeInterceptor implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //Cast
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession();
        String autenticat = (String)session.getAttribute("autenticat");
        String time = (String)session.getAttribute("lastActiviti");

        if(autenticat != null && autenticat.equals("SI")) {
            //Patró: chain of responsibility
            filterChain.doFilter(servletRequest,servletResponse);
            if(true){

                session.invalidate();
            }
        }else{
            response.sendRedirect("login");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
