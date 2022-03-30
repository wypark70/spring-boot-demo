package com.example.bootdemo;

import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class HideUserGradeFilterTest {
  @Spy
  private HideUserGradeFilter filter;
  private static String json;

  @BeforeAll
  static void beforeAll() {
    log.debug("Before all test methods");
    log.debug("Before each test method");
    User user = User.builder()
        .name("박우용")
        .gradeCode("C001")
        .gradeName("현장관리자")
        .age(10000)
        .build();
    Gson gson = new Gson();
    json = gson.toJson(user);
  }

  @AfterAll
  static void afterAll() {
    log.debug("After all test methods");
  }

  @BeforeEach
  void beforeEach() {
    log.debug("Before each test method");
  }

  @AfterEach
  void afterEach() {
    log.debug("After each test method");
  }

  @DisplayName("직급 정보가 마스킹하여 반환한다.")
  @Test
  @Disabled
  void replaceGradeTest() {
    // given

    // when
    log.debug("json: {}", json);
    String result = ReflectionTestUtils.invokeMethod(filter, "replaceGrade", json);
    log.debug("result: {}", result);
    DocumentContext context = JsonPath.parse(result);
    String gradeName = context.read("$.gradeName");

    // then
    assertEquals("*******", gradeName);
  }

  @DisplayName("직급 정보가 마스킹 된다.")
  @Test
  void doFilterTest() throws ServletException, IOException {
    //given
    HttpServletRequest request = new MockHttpServletRequest();
    HttpServletResponse response = new MockHttpServletResponse();
    FilterChain chain = new MockFilterChain();

    response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
    response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
    response.getWriter().write(json);

    HideUserGradeResponseWrapper hideUserGradeResponseWrapper = mock(HideUserGradeResponseWrapper.class);
    when(hideUserGradeResponseWrapper.getContentAsString()).thenReturn(json);
    when(filter.getHideUserGradeResponseWrapper(response)).thenReturn(hideUserGradeResponseWrapper);


    // when
    filter.doFilter(request, response, chain);

    // then
    log.debug("doFilterTest");
  }
}