package com.example.springapply.config;

import com.example.springapply.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {


    //@Bean
    public FilterRegistrationBean<MyFilter> secondFilter() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        registrationBean.setName("MY-filter");
        return registrationBean;
    }


}
