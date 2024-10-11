package com.systex.lottery.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.systex.lottery.model.AccountRepository;

@Configuration
public class FilterConfig {

    @Autowired
    private AccountRepository accountRepository;

    @Bean
    FilterRegistrationBean<LoginFilter> authFilter() {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoginFilter(accountRepository));
        registrationBean.addUrlPatterns("/*"); // 過濾所有頁面
        registrationBean.setOrder(1); // 設置過濾器順序

        return registrationBean;
    }
}
