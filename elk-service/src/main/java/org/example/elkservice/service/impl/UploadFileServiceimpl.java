package org.example.elkservice.service.impl;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.example.elkservice.entity.vo.MinioProperties;
import org.example.elkservice.service.UploadFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class UploadFileServiceimpl implements UploadFileService {

    @Override
    public String uploadFile(MultipartFile file, String filePath) {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://172.31.38.196:9000") // MinIO 地址
                // accessKey 和 secretKey
                .credentials("HpVGpDbrdSlwVSoGpm6s", "CUZkOgcXNjNXBWfl79WaS6gClXHXRD5I69fyktwE")
                .build();
        try (InputStream in = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket("datasets")
                            .object(file.getOriginalFilename())
                            .stream(in, file.getSize(), -1)
                            .contentType(file.getContentType()) // 可根据文件类型设置
                            .build()
            );
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败" + e.getMessage();
        }
    }
}
