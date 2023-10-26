package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class RightsizingVm {
  private String name;
  private String status;
  private String metricType;
  private int beforeValue;
  private int afterValue;

  @Builder
  public RightsizingVm(String name, String status, String metricType, int beforeValue,
      int afterValue) {
    this.name = name;
    this.status = status;
    this.metricType = metricType;
    this.beforeValue = beforeValue;
    this.afterValue = afterValue;
  }
}
