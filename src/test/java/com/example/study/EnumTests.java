package com.example.study;

import com.example.study.enums.MetricType;
import com.example.study.enums.Status;
import com.example.study.model.Effect;
import com.example.study.model.RightsizingResponse;
import com.example.study.model.RightsizingVm;
import com.example.study.model.RightsizingVm2;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnumTests {

  List<RightsizingVm2> rightsizingVms = new ArrayList<>();

  @BeforeEach
  public void setup() {
    RightsizingVm2 vm1 = new RightsizingVm2("vm1", Status.oversized, MetricType.cpu, 10, 5);
    RightsizingVm2 vm2 = new RightsizingVm2("vm2", Status.oversized, MetricType.memory, 10, 5);
    RightsizingVm2 vm3 = new RightsizingVm2("vm3", Status.oversized, MetricType.disk, 20, 10);
    RightsizingVm2 vm4 = new RightsizingVm2("vm4", Status.undersized, MetricType.cpu, 10, 20);
    RightsizingVm2 vm5 = new RightsizingVm2("vm5", Status.undersized, MetricType.memory, 10, 20);
    RightsizingVm2 vm6 = new RightsizingVm2("vm6", Status.inactive, null, 0, 0);

    rightsizingVms.add(vm1);
    rightsizingVms.add(vm2);
    rightsizingVms.add(vm3);
    rightsizingVms.add(vm4);
    rightsizingVms.add(vm5);
    rightsizingVms.add(vm6);
  }

  @Test
  public void 열거형_적용() {
    // Enum 타입을 통해 문자열이지만 정해진 상수로 코드 안정성 및 가독성을 향상시킨다.
    List<RightsizingVm2> rightsizingVms = new ArrayList<>();

    List<RightsizingVm2> targetVms = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals(Status.oversized) || vm.getStatus().equals(Status.undersized))
        .collect(Collectors.toList());

    Effect cpuEffect = getEffectByMetricType(targetVms, MetricType.cpu);
    Effect memoryEffect = getEffectByMetricType(targetVms, MetricType.memory);
    Effect diskEffect = getEffectByMetricType(targetVms, MetricType.disk);

    RightsizingResponse response = RightsizingResponse.builder()
        .cpu(cpuEffect)
        .memory(memoryEffect)
        .disk(diskEffect)
        .build();

    System.out.println(" response = " + response);
  }

  private Effect getEffectByMetricType(List<RightsizingVm2> rightsizingVms, MetricType metricType) {
    int beforeMetricSum = rightsizingVms.stream()
        .filter(vm -> vm.getMetricType().equals(metricType))
        .mapToInt(RightsizingVm2::getBeforeValue)
        .sum();

    int afterMetricSum = rightsizingVms.stream()
        .filter(vm -> vm.getMetricType().equals(metricType))
        .mapToInt(RightsizingVm2::getAfterValue)
        .sum();

    return Effect.builder()
        .beforeValue(beforeMetricSum)
        .afterValue(afterMetricSum)
        .build();
  }
}
