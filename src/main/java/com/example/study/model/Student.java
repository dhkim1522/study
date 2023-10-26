package com.example.study.model;

public class Student {
  private String name;
  private String title;

  public Student(String name, String title) {
    this.name = name;
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", title='" + title + '\'' +
        '}';
  }
}
