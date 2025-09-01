package com.example.uploader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Bean
    public S3Client s3Client(AppProperties appProperties) {
        AppProperties.S3 s3Props = appProperties.getS3();
        AwsCredentialsProvider credsProvider;

        if (s3Props.getAccessKeyId() != null && !s3Props.getAccessKeyId().isBlank()
                && s3Props.getSecretAccessKey() != null && !s3Props.getSecretAccessKey().isBlank()) {

            AwsBasicCredentials creds = AwsBasicCredentials.create(s3Props.getAccessKeyId(), s3Props.getSecretAccessKey());
            credsProvider = StaticCredentialsProvider.create(creds);
        } else {
            credsProvider = DefaultCredentialsProvider.create();
        }

        return S3Client.builder()
                .region(Region.of(s3Props.getRegion()))
                .credentialsProvider(credsProvider)
                .build();
    }
}