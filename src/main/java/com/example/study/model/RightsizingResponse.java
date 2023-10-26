package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RightsizingResponse {
  private Effect cpu;
  private Effect memory;
  private Effect disk;

  @Builder
  public RightsizingResponse(Effect cpu, Effect memory, Effect disk) {
    this.cpu = cpu;
    this.memory = memory;
    this.disk = disk;
  }
}
