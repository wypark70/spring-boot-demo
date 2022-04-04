package com.example.bootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersUiController {
  final UserService service;

  public UsersUiController(UserService service) {
    this.service = service;
  }

  @GetMapping(value = "/ui/users")
  public List<User> users() {
    return service.getUsers();
  }
  @GetMapping(value = "/ui/users/100")
  public User users100() {
    return service.getUserById(100L);
  }

  @GetMapping(value = "/ui/users/200")
  public User users200() {
    return service.getUserById(200L);
  }
}
