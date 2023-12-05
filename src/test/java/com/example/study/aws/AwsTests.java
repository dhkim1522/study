package com.example.study.aws;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;
import software.amazon.awssdk.services.s3.S3Client;

import static software.amazon.awssdk.regions.Region.AP_NORTHEAST_2;

public class AwsTests {

    // 테스트때만 입력
    String accessKey = "";
    String secretKey = "";

    @Test
    void awsEC2Test() {
        Ec2Client ec2Client = Ec2Client.builder()
                .region(AP_NORTHEAST_2)
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .build();


        // EC2 인스턴스 목록 조회 요청
        DescribeInstancesRequest describeInstancesRequest = DescribeInstancesRequest.builder().build();
        DescribeInstancesResponse response = ec2Client.describeInstances(describeInstancesRequest);

        // 조회 결과 출력
        System.out.println("EC2 인스턴스 목록:");

        for (Reservation reservation : response.reservations()) {
            for (Instance instance : reservation.instances()) {
                System.out.println("Instance ID: " + instance.instanceId());
                System.out.println("State: " + instance.state().name());
                // 필요한 다른 정보들을 출력하거나 처리할 수 있습니다.
                System.out.println("---------------------------");
            }
        }

        // EC2 클라이언트 종료
        ec2Client.close();
    }

    @Test
    void awsS3Test() {
        // S3 클라이언트 생성
        S3Client s3Client = S3Client.builder()
                .region(AP_NORTHEAST_2)
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .build();

        s3Client.listBuckets()
                .buckets()
                .forEach(bucket -> System.out.println("Bucket Name: " + bucket.name()));
    }

}
