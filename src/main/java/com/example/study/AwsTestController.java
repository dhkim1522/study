package com.example.study;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.Filter;
import software.amazon.awssdk.services.ec2.model.Instance;

import java.util.List;
import java.util.stream.Collectors;

import static software.amazon.awssdk.regions.Region.AP_NORTHEAST_2;

@RestController
public class AwsTestController {

    @GetMapping("/test")
    void getAwsEc2() {
        String accessKey = "AKIAROMCZ63PP5CGILAE";
        String secretKey = "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";

        Ec2Client ec2Client = Ec2Client.builder()
                .region(AP_NORTHEAST_2)
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .build();

        //Instance 불러오기
        DescribeInstancesRequest describeInstancesRequest = DescribeInstancesRequest.builder()
                .filters(
                        Filter.builder()
                                .name("instance-state-name")
                                .values("running","stopped")
                                .build()
                ).build();

        List<Instance> instances = ec2Client.describeInstances(describeInstancesRequest).reservations().stream()
                .flatMap(reservation -> reservation.instances().stream())
                .collect(Collectors.toList());
    }
}
