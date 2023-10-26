package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Effect {
  private int beforeValue;
  private int afterValue;

  @Builder
  public Effect(int beforeValue, int afterValue) {
    this.beforeValue = beforeValue;
    this.afterValue = afterValue;
  }
}
