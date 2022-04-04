package com.example.bootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersUiTest1Controller {
  final UserService service;

  public UsersUiTest1Controller(UserService service) {
    this.service = service;
  }

  @GetMapping(value = "/ui/test1/users")
  public List<User> users() {
    return service.getUsers();
  }

  @GetMapping(value = "/ui/test1/users/100")
  public User users100() {
    return service.getUserById(100L);
  }

  @GetMapping(value = "/ui/test1/users/200")
  public User users200() {
    return service.getUserById(200L);
  }
}
