package com.example.bootdemo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean<HideUserGradeFilter> replaceHtmlFilter() {
    FilterRegistrationBean<HideUserGradeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new HideUserGradeFilter());
    filterRegistrationBean.addUrlPatterns("/ui/users/*");
    return filterRegistrationBean;
  }
}
