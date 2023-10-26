package com.example.study;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class NullTests {

  @Test
  void 널_처리_테스트() {

    Map<String, Integer> map = new HashMap<>();

    map.put("1", 100);
    map.put("2", 200);

    map.get(null);

  }
}
