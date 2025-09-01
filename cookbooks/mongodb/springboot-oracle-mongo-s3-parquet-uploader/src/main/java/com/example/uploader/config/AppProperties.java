package com.example.uploader.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app")
@Validated
public class AppProperties {

    private final Job job = new Job();
    private final Output output = new Output();
    private final Keys keys = new Keys();
    private final S3 s3 = new S3();

    public Job getJob() { return job; }
    public Output getOutput() { return output; }
    public Keys getKeys() { return keys; }
    public S3 getS3() { return s3; }

    @Validated
    public static class Job {
        @NotBlank
        private String oracleSql = "SELECT ID, NAME, KEY_FIELD FROM MY_TABLE";
        public String getOracleSql() { return oracleSql; }
        public void setOracleSql(String oracleSql) { this.oracleSql = oracleSql; }
    }

    @Validated
    public static class Keys {
        @NotBlank
        private String oracleKeyColumn = "KEY_FIELD";
        @NotBlank
        private String mongoCollection = "my_collection";
        @NotBlank
        private String mongoKeyField = "_id";

        public String getOracleKeyColumn() { return oracleKeyColumn; }
        public void setOracleKeyColumn(String oracleKeyColumn) { this.oracleKeyColumn = oracleKeyColumn; }
        public String getMongoCollection() { return mongoCollection; }
        public void setMongoCollection(String mongoCollection) { this.mongoCollection = mongoCollection; }
        public String getMongoKeyField() { return mongoKeyField; }
        public void setMongoKeyField(String mongoKeyField) { this.mongoKeyField = mongoKeyField; }
    }

    @Validated
    public static class Output {
        @Min(100) @Max(5000)
        private int rowsPerFile = 1000;

        /**
         * One of: jsonl, jsonarray, parquet
         */
        @NotBlank
        private String format = "jsonl";

        @NotBlank
        private String s3KeyPrefix = "exports/";

        /**
         * For parquet, the extension will be forced to .parquet (snappy).
         * For jsonl/jsonarray, this pattern applies with %s timestamp, %d part, %s ext (jsonl/json).
         */
        @NotBlank
        private String fileNamePattern = "export_%s_part%05d.%s";

        public int getRowsPerFile() { return rowsPerFile; }
        public void setRowsPerFile(int rowsPerFile) { this.rowsPerFile = rowsPerFile; }
        public String getFormat() { return format; }
        public void setFormat(String format) { this.format = format; }
        public String getS3KeyPrefix() { return s3KeyPrefix; }
        public void setS3KeyPrefix(String s3KeyPrefix) { this.s3KeyPrefix = s3KeyPrefix; }
        public String getFileNamePattern() { return fileNamePattern; }
        public void setFileNamePattern(String fileNamePattern) { this.fileNamePattern = fileNamePattern; }
    }

    @Validated
    public static class S3 {
        @NotBlank
        private String region = "us-east-1";
        @NotBlank
        private String bucket;

        private String accessKeyId;
        private String secretAccessKey;
        private String sessionToken;

        public String getRegion() { return region; }
        public void setRegion(String region) { this.region = region; }
        public String getBucket() { return bucket; }
        public void setBucket(String bucket) { this.bucket = bucket; }
        public String getAccessKeyId() { return accessKeyId; }
        public void setAccessKeyId(String accessKeyId) { this.accessKeyId = accessKeyId; }
        public String getSecretAccessKey() { return secretAccessKey; }
        public void setSecretAccessKey(String secretAccessKey) { this.secretAccessKey = secretAccessKey; }
        public String getSessionToken() { return sessionToken; }
        public void setSessionToken(String sessionToken) { this.sessionToken = sessionToken; }
    }
}