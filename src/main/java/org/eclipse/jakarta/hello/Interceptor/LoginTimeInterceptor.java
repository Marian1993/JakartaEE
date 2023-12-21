package org.eclipse.jakarta.hello.Interceptor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

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
        LocalDateTime activiti = (LocalDateTime) session.getAttribute("lastActiviti");
        LocalDateTime now =LocalDateTime.now();

        if(activiti !=null && activiti.plusSeconds(30).isBefore(now)){
            System.out.println("entra");
            session.invalidate();
            response.sendRedirect("login");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
