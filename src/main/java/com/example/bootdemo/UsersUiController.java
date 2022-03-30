package com.example.bootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersUiController {
  @GetMapping(value = "/ui/users/100")
  public User users100() {
    return User.builder()
        .name("박우용")
        .gradeCode("C001")
        .gradeName("현장관리자")
        .build();
  }

  @GetMapping(value = "/ui/users/200")
  public User users200() {
    return User.builder()
        .name("박은용")
        .gradeCode("C002")
        .gradeName("현장소장")
        .build();
  }
}
