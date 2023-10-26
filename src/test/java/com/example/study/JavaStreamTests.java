package com.example.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JavaStreamTests {

  List<Dish> menu = new ArrayList();

  @BeforeEach
  public void before() {
    Dish dish1 = new Dish("인스턴트", "감자튀김", 500);
    Dish dish2 = new Dish("육류", "갈비", 1300);
    Dish dish3 = new Dish("인스턴트", "치킨", 2000);
    Dish dish4 = new Dish("채소류", "샐러리", 90);
    Dish dish5 = new Dish("일식", "회", 300);
    Dish dish6 = new Dish("한식", "떡볶이", 1100);

    menu.add(dish1);
    menu.add(dish2);
    menu.add(dish3);
    menu.add(dish4);
    menu.add(dish5);
    menu.add(dish6);
  }

  @Test
  void listFilterTest() {
    List<Dish> highCalorieDishes = new ArrayList<>();

    // 음식중 칼로리가 1000 보다 높은 음식 객체를 List에 저장
    for (Dish dish : menu) {
      if (dish.getCalorie() > 1000) {
        highCalorieDishes.add(dish);
      }
    }

    int summary = 0;

    // 1000 보다 높은 칼로리 합산
    for (Dish dish : highCalorieDishes) {
      summary += dish.getCalorie();
    }

    System.out.println(" summary = " + summary);
  }

  @Test
  void streamFilterTest() {

    int summary = menu.stream()
        .filter(dish -> dish.getCalorie() > 1000)
        .mapToInt(Dish::getCalorie)
        .sum();

    System.out.println(" summary = " + summary);
  }

}
