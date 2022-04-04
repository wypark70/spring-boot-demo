package com.example.bootdemo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  @Override
  public List<User> getUsers() {
    List<User> users = new ArrayList<>();
    users.add(this.getUserById(100L));
    users.add(this.getUserById(200L));
    return users;
  }

  @Override
  public User getUserById(Long id) {
    if (id == 100L) {
      return User.builder()
          .name("박우용")
          .gradeCode("C001")
          .gradeName("현장관리자")
          .build();
    } else if (id == 200L) {
      return User.builder()
          .name("박은용")
          .gradeCode("C002")
          .gradeName("현장소장")
          .build();
    }
    return null;
  }

}
