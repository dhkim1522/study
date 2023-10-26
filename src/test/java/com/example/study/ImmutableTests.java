package com.example.study;

import com.example.study.model.ImmutableStudent;
import com.example.study.model.Student;
import org.junit.jupiter.api.Test;

public class ImmutableTests {

  @Test
  void 가변_객체_테스트() {
    Student student = new Student("김동현", "사원");

    System.out.println(" student = " + student);

    student.setName("이지봉");
    student.setTitle("파트장");

    System.out.println(" setter change student = " + student);
  }

  @Test
  void 불변_객체_테스트() {
    ImmutableStudent studentA = ImmutableStudent.defaultTitleStudent("김동현");
    ImmutableStudent studentB = ImmutableStudent.of("이지봉", "파트장");

    System.out.println(" studentA = " + studentA);
    System.out.println(" studentB = " + studentB);
  }

}
