package com.example.study.model;

import com.example.study.enums.MetricType;
import com.example.study.enums.Status;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RightsizingVm2 { // 예제를 위해 클래스명을 2를 붙혀지었지만 실제로는 이렇게 사용하면 안됨
  private String name;
  private Status status;
  private MetricType metricType;
  private int beforeValue;
  private int afterValue;

  @Builder
  public RightsizingVm2(String name, Status status, MetricType metricType, int beforeValue,
      int afterValue) {
    this.name = name;
    this.status = status;
    this.metricType = metricType;
    this.beforeValue = beforeValue;
    this.afterValue = afterValue;
  }
}
