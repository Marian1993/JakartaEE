package org.eclipse.jakarta.hello.Interceptor;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//Tots els directoris que estiguin davall aquest directori executara aquesta pagina
//@WebFilter( urlPatterns = {"/admin/*"});

//@WebFilter(servletNames = {"Photos","PhotosForm"})
public class LoginInterceptor implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
