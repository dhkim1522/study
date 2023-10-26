package com.example.study.model;

public final class ImmutableStudent {
  private final String name;
  private final String title;

  private ImmutableStudent(String name, String title) {
    this.name = name;
    this.title = title;
  }

  public static ImmutableStudent of(String name, String title) {
    return new ImmutableStudent(name, title);
  }

  public static ImmutableStudent defaultTitleStudent(String name) {
    return new ImmutableStudent(name, "사원");
  }

  public String getName() {
    return name;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "ImmutableStudent{" +
        "name='" + name + '\'' +
        ", title='" + title + '\'' +
        '}';
  }
}
