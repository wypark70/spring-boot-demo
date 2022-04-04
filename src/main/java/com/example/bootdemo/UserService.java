package com.example.bootdemo;

import java.util.List;

interface UserService {
  List<User> getUsers();
  User getUserById(Long id);
}
