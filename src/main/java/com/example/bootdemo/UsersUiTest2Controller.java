package com.example.bootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersUiTest2Controller {
  final UserService service;

  public UsersUiTest2Controller(UserService service) {
    this.service = service;
  }

  @GetMapping(value = "/ui/test2/users")
  public List<User> users() {
    return service.getUsers();
  }

  @GetMapping(value = "/ui/test2/users/100")
  public User users100() {
    return service.getUserById(100L);
  }

  @GetMapping(value = "/ui/test2/users/200")
  public User users200() {
    return service.getUserById(200L);
  }
}
