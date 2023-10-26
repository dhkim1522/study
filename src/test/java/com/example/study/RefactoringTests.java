package com.example.study;

import com.example.study.model.Effect;
import com.example.study.model.RightsizingResponse;
import com.example.study.model.RightsizingVm;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RefactoringTests {

  List<RightsizingVm> rightsizingVms = new ArrayList<>();

  @BeforeEach
  public void setup() {
    RightsizingVm vm1 = new RightsizingVm("vm1", "oversized", "cpu", 10, 5);
    RightsizingVm vm2 = new RightsizingVm("vm2", "oversized", "memory", 10, 5);
    RightsizingVm vm3 = new RightsizingVm("vm3", "oversized", "disk", 20, 10);
    RightsizingVm vm4 = new RightsizingVm("vm4", "undersized", "cpu", 10, 20);
    RightsizingVm vm5 = new RightsizingVm("vm5", "undersized", "memory", 10, 20);
    RightsizingVm vm6 = new RightsizingVm("vm6", "inactive", null, 0, 0);

    rightsizingVms.add(vm1);
    rightsizingVms.add(vm2);
    rightsizingVms.add(vm3);
    rightsizingVms.add(vm4);
    rightsizingVms.add(vm5);
    rightsizingVms.add(vm6);
  }

  @Test
  public void 초기_로직() {

    // 최종 반환 값을 위한 변수 선언
    int beforeCpu = 0;
    int beforeMemory = 0;
    int beforeDisk = 0;

    int afterCpu = 0;
    int afterMemory = 0;
    int afterDisk = 0;

    // targetVms 순회하며 status 가 oversized, undersized 인 경우에 metricType 별 로
    // before, after 값의 총합을 구한다.
    for (RightsizingVm vm : rightsizingVms) {
      switch (vm.getStatus()) {
        case "oversized" :
          if ("cpu".equals(vm.getMetricType())) {
            beforeCpu += vm.getBeforeValue();
            afterCpu += vm.getAfterValue();
          }
          if ("memory".equals(vm.getMetricType())) {
            beforeMemory += vm.getBeforeValue();
            afterMemory += vm.getAfterValue();
          }
          if ("disk".equals(vm.getMetricType())) {
            beforeDisk += vm.getBeforeValue();
            afterDisk += vm.getAfterValue();
          }
          break;
        case "undersized" :
          if ("cpu".equals(vm.getMetricType())) {
            beforeCpu += vm.getBeforeValue();
            afterCpu += vm.getAfterValue();
          }
          if ("memory".equals(vm.getMetricType())) {
            beforeMemory += vm.getBeforeValue();
            afterMemory += vm.getAfterValue();
          }
          if ("disk".equals(vm.getMetricType())) {
            beforeDisk += vm.getBeforeValue();
            afterDisk += vm.getAfterValue();
          }
          break;
      }
    }

    // 객체 선언
    Effect cpuEffect = new Effect();
    Effect memoryEffect = new Effect();
    Effect diskEffect = new Effect();

    // 각 객체에 setter 를 통한 값 할당
    cpuEffect.setBeforeValue(beforeCpu);
    cpuEffect.setAfterValue(afterCpu);

    memoryEffect.setBeforeValue(beforeMemory);
    memoryEffect.setAfterValue(afterMemory);

    diskEffect.setBeforeValue(beforeDisk);
    diskEffect.setAfterValue(afterDisk);

    // 최종 반환 객체에 생성자를 통해 할당
    RightsizingResponse response = new RightsizingResponse(cpuEffect, memoryEffect, diskEffect);

    System.out.println(" response = " + response);
  }

  @Test
  public void 깊은_중첩문_스트림_변환() {

    // status 가 oversized, undersized 인 요소를 대상으로 메트릭 타입 별 before, after 총합을 구한 후
    // 각 변수에 즉시 할당한다.
    int beforeCpu = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .filter(vm -> vm.getMetricType().equals("cpu"))
        .mapToInt(RightsizingVm::getBeforeValue)
        .sum();

    int beforeMemory = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .filter(vm -> vm.getMetricType().equals("memory"))
        .mapToInt(RightsizingVm::getBeforeValue)
        .sum();

    int beforeDisk = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .filter(vm -> vm.getMetricType().equals("disk"))
        .mapToInt(RightsizingVm::getBeforeValue)
        .sum();

    int afterCpu = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .filter(vm -> vm.getMetricType().equals("cpu"))
        .mapToInt(RightsizingVm::getAfterValue)
        .sum();

    int afterMemory = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .filter(vm -> vm.getMetricType().equals("memory"))
        .mapToInt(RightsizingVm::getAfterValue)
        .sum();

    int afterDisk = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .filter(vm -> vm.getMetricType().equals("disk"))
        .mapToInt(RightsizingVm::getAfterValue)
        .sum();

    // 객체 선언
    Effect cpuEffect = new Effect();
    Effect memoryEffect = new Effect();
    Effect diskEffect = new Effect();

    // 각 객체에 setter 를 통한 값 할당
    cpuEffect.setBeforeValue(beforeCpu);
    cpuEffect.setAfterValue(afterCpu);

    memoryEffect.setBeforeValue(beforeMemory);
    memoryEffect.setAfterValue(afterMemory);

    diskEffect.setBeforeValue(beforeDisk);
    diskEffect.setAfterValue(afterDisk);

    // 최종 반환 객체에 생성자를 통해 할당
    RightsizingResponse response = new RightsizingResponse(cpuEffect, memoryEffect, diskEffect);

    System.out.println(" response = " + response);
  }

  @Test
  public void 중복코드제거() {

    // status 가 oversized, undersized 인 요소를 대상으로만 자원 총합을 구하기 위해 새로운 List를 생성한다.
    List<RightsizingVm> targetVms = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .collect(Collectors.toList());

    // 메트릭 타입 별 before, after 총합을 구한 후
    // 각 변수에 즉시 할당한다.
    int beforeCpu = targetVms.stream()
        .filter(vm -> vm.getMetricType().equals("cpu"))
        .mapToInt(RightsizingVm::getBeforeValue)
        .sum();

    int beforeMemory = targetVms.stream()
        .filter(vm -> vm.getMetricType().equals("memory"))
        .mapToInt(RightsizingVm::getBeforeValue)
        .sum();

    int beforeDisk = targetVms.stream()
        .filter(vm -> vm.getMetricType().equals("disk"))
        .mapToInt(RightsizingVm::getBeforeValue)
        .sum();

    int afterCpu = targetVms.stream()
        .filter(vm -> vm.getMetricType().equals("cpu"))
        .mapToInt(RightsizingVm::getAfterValue)
        .sum();

    int afterMemory = targetVms.stream()
        .filter(vm -> vm.getMetricType().equals("memory"))
        .mapToInt(RightsizingVm::getAfterValue)
        .sum();

    int afterDisk = targetVms.stream()
        .filter(vm -> vm.getMetricType().equals("disk"))
        .mapToInt(RightsizingVm::getAfterValue)
        .sum();

    // 객체 선언
    Effect cpuEffect = new Effect();
    Effect memoryEffect = new Effect();
    Effect diskEffect = new Effect();

    // 각 객체에 setter 를 통한 값 할당
    cpuEffect.setBeforeValue(beforeCpu);
    cpuEffect.setAfterValue(afterCpu);

    memoryEffect.setBeforeValue(beforeMemory);
    memoryEffect.setAfterValue(afterMemory);

    diskEffect.setBeforeValue(beforeDisk);
    diskEffect.setAfterValue(afterDisk);

    // 최종 반환 객체에 생성자를 통해 할당
    RightsizingResponse response = new RightsizingResponse(cpuEffect, memoryEffect, diskEffect);

    System.out.println(" response = " + response);
  }

  @Test
  public void 메소드_분리() {

    // status 가 oversized, undersized 인 요소를 대상으로만 자원 총합을 구하기 위해 새로운 List를 생성한다.
    List<RightsizingVm> targetVms = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .collect(Collectors.toList());

    // 분리된 메소드를 통해 메트릭 타입별 Effect 객체를 생성한 후 할당한다.
    Effect cpuEffect = getEffectByMetricType(targetVms, "cpu");
    Effect memoryEffect = getEffectByMetricType(targetVms, "memory");
    Effect diskEffect = getEffectByMetricType(targetVms, "disk");

    // 최종 반환 객체에 생성자를 통해 할당
    RightsizingResponse response = new RightsizingResponse(cpuEffect, memoryEffect, diskEffect);

    System.out.println(" response = " + response);
  }

  @Test
  public void 빌더패턴_적용() {

    // status 가 oversized, undersized 인 요소를 대상으로만 자원 총합을 구하기 위해 새로운 List를 생성한다.
    List<RightsizingVm> targetVms = rightsizingVms.stream()
        .filter(vm -> vm.getStatus().equals("oversized") || vm.getStatus().equals("undersized"))
        .collect(Collectors.toList());

    // 분리된 메소드를 통해 메트릭 타입별 Effect 객체를 생성한 후 할당한다.
    Effect cpuEffect = getEffectByMetricType(targetVms, "cpu");
    Effect memoryEffect = getEffectByMetricType(targetVms, "memory");
    Effect diskEffect = getEffectByMetricType(targetVms, "disk");

    // 최종 반환 객체에 생성자를 통해 할당, 코드 라인은 더 많아지지만 객체 가독성이 더욱 향상되었다.
    RightsizingResponse response = RightsizingResponse.builder()
        .cpu(cpuEffect)
        .memory(memoryEffect)
        .disk(diskEffect)
        .build();

    System.out.println(" response = " + response);
  }

  private Effect getEffectByMetricType(List<RightsizingVm> rightsizingVms, String metricType) {
    // 파라미터로 받은 메트릭 타입에 해당하는 요소만을 대상으로 before 총합을 구한다.
    int beforeMetricSum = rightsizingVms.stream()
        .filter(vm -> vm.getMetricType().equals(metricType))
        .mapToInt(RightsizingVm::getBeforeValue)
        .sum();

    // 파라미터로 받은 메트릭 타입에 해당하는 요소만을 대상으로 after 총합을 구한다.
    int afterMetricSum = rightsizingVms.stream()
        .filter(vm -> vm.getMetricType().equals(metricType))
        .mapToInt(RightsizingVm::getAfterValue)
        .sum();

    // 최종 반환 객체에 생성자를 통해 할당, 코드 라인은 더 많아지지만 객체 가독성이 더욱 향상되었다.
    return Effect.builder()
        .beforeValue(beforeMetricSum)
        .afterValue(afterMetricSum)
        .build();
  }
}
