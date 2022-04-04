package com.example.bootdemo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Configuration
public class FilterConfig {
  @Bean
  public FilterRegistrationBean<HideUserGradeFilter> hideUserGradeFilter() {
    FilterRegistrationBean<HideUserGradeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new HideUserGradeFilter() {
      @Override
      protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        List<String> regexList = Arrays.asList(
            "/ui/(test1)/users/?.*",
            "/ui/(test2)/users/?.*");
        return regexList.stream().noneMatch(regex -> Pattern.matches(regex, path));
      }
    });
    filterRegistrationBean.addUrlPatterns("/ui/*");

    return filterRegistrationBean;
  }
}
