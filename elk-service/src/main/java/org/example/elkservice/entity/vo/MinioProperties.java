package org.example.elkservice.entity.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class MinioProperties {

    @Value("${minio.endpoint}")
    public String endpoint;

    @Value("${minio.access-key}")
    public String accessKey;

    @Value("${minio.secret-key}")
    public String secretKey;

    @Value("${minio.bucket}")
    public String bucket;

}
