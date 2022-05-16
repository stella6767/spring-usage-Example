package com.example.springapply.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("my filter");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 1. @Component 방식은 @Order로 순서는 지정할 수 있으나 url패턴 매핑이 불가
     * 2. @ServletComponentScan + @WebFilter 방식은 url매핑은 가능하나 순서 지정 불가
     * https://jronin.tistory.com/124
     *
     */
}
