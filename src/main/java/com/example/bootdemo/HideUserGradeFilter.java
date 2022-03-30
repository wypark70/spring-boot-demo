package com.example.bootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class HideUserGradeFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    HideUserGradeResponseWrapper hideUserGradeResponseWrapper = getHideUserGradeResponseWrapper(response);
    filterChain.doFilter(request, hideUserGradeResponseWrapper);
    log.info("contentType: {}", response.getContentType());
    if (response.getContentType() != null && response.getContentType().contains(MediaType.APPLICATION_JSON.toString())) {
      String content = hideUserGradeResponseWrapper.getContentAsString();
      log.info("content: {}", content);
      String replacedContent = replaceGrade(content);
      log.info("replacedContent: {}", replacedContent);
      response.getWriter().write(replacedContent);
    }
  }

  protected HideUserGradeResponseWrapper getHideUserGradeResponseWrapper(ServletResponse response) {
    return new HideUserGradeResponseWrapper((HttpServletResponse) response);
  }

  private String replaceGrade(String content) {
    Pattern replacePattern = Pattern.compile("\"(gradeCode|gradeName)\":\"(.*?)\"");
    Matcher matcher = replacePattern.matcher(content);
    String replacedContent = String.copyValueOf(content.toCharArray());
    while (matcher.find()) {
      String matchString = matcher.group(0);
      String replaceString = matchString.replace(matcher.group(2), "*******");
      replacedContent = replacedContent.replace(matchString, replaceString);
    }
    return replacedContent;
  }
}