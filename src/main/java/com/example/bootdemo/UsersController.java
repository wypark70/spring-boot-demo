package com.example.bootdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
  final UserService service;

  public UsersController(UserService service) {
    this.service = service;
  }

  @GetMapping(value = "/users")
  public List<User> users() {
    return service.getUsers();
  }
  @GetMapping(value = "/users/100")
  public User users100() {
    return service.getUserById(100L);
  }

  @GetMapping(value = "/users/200")
  public User users200() {
    return service.getUserById(200L);
  }
}
