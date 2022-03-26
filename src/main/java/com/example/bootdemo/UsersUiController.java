package com.example.bootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsersUiController {
  @GetMapping(value = "/UI/users/100")
  public Map<String, Object> users100() {
    Map<String, Object> returnMap = new HashMap<>();
    returnMap.put("id", 100);
    returnMap.put("name", "박우용");
    returnMap.put("gradeName", "사장");
    returnMap.put("epid", "034234324234");
    return returnMap;
  }

  @GetMapping(value = "/UI/users/200")
  public Map<String, Object> users200() {
    Map<String, Object> returnMap = new HashMap<>();
    returnMap.put("id", 200);
    returnMap.put("name", "박은용");
    returnMap.put("gradeName", "회장");
    returnMap.put("epid", "034234324000");
    return returnMap;
  }
}
