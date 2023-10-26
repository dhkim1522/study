package com.example.study;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
  private String category;
  private String name;
  private int calorie;
}
