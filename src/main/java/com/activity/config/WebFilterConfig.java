package com.activity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/8/16 17:02
 * @description：配置过滤器
 * @modified By：
 * @version:
 */

@Configuration
public class WebFilterConfig {

    @Autowired
    private AuthenFilter authenFilter;

    @Bean
    public FilterRegistrationBean setFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(authenFilter);
        registrationBean.addUrlPatterns("/wx/api/*");
        registrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registrationBean;
    }
}
