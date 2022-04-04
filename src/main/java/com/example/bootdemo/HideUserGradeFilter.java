package com.example.bootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class HideUserGradeFilter extends OncePerRequestFilter {

  protected HideUserGradeResponseWrapper getHideUserGradeResponseWrapper(ServletResponse response) {
    return new HideUserGradeResponseWrapper((HttpServletResponse) response);
  }

  private String replaceGrade(String content, String encode) throws UnsupportedEncodingException {
    Pattern replacePattern = Pattern.compile("\"(gradeCode|gradeName)\":\"(.*?)\"");
    Matcher matcher = replacePattern.matcher(content);
    String replacedContent = new String(content.getBytes(encode), encode);
    while (matcher.find()) {
      String matchString = matcher.group(0);
      String replaceString = matchString.replace(matcher.group(2), "*******");
      replacedContent = replacedContent.replace(matchString, replaceString);
    }
    return replacedContent;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    HideUserGradeResponseWrapper hideUserGradeResponseWrapper = getHideUserGradeResponseWrapper(response);
    filterChain.doFilter(request, hideUserGradeResponseWrapper);
    log.info("contentType: {}", response.getContentType());
    if (response.getContentType() != null && response.getContentType().contains(MediaType.APPLICATION_JSON.toString())) {
      String content = hideUserGradeResponseWrapper.getContentAsString();
      log.info("content: {}", content);
      String replacedContent = replaceGrade(content, response.getCharacterEncoding());
      log.info("replacedContent: {}", replacedContent);
      response.getWriter().write(replacedContent);
    }
  }
}