package com.example.bootdemo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String name;
  private String gradeCode;
  private String gradeName;
  private int age;
}
