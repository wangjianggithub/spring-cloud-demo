//package org.example.elkservice.controller;
//
//import io.minio.MinioClient;
//import io.minio.PutObjectArgs;
//import org.example.elkservice.entity.vo.MinioProperties;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//public class MinioUnitTest {
//
//    public static void main(String[] args) {
//        MinioProperties prop = new MinioProperties();
//        try {
//            // 1. 初始化 MinIO 客户端
//            MinioClient minioClient = MinioClient.builder()
//                    .endpoint(prop.endpoint) // MinIO 地址
//                    // accessKey 和 secretKey
//                    .credentials(accessKey, secretKey)
//                    .build();
//
//            // 2. 文件信息
//            String bucketName = bucket;
//            String objectName = "mydataset1/images/xiaoyuanjiang.webp"; // 上传路径（类似目录）
//            String filePath = "/Users/zhangyong/Pictures/xiaoyuanjiang.webp"; // 本地文件路径
//
//            // 3. 上传文件（流式上传）
//            try (InputStream in = new FileInputStream(filePath)) {
//                minioClient.putObject(
//                        PutObjectArgs.builder()
//                                .bucket(bucketName)
//                                .object(objectName)
//                                .stream(in, in.available(), -1)
//                                .contentType("image/png") // 可根据文件类型设置
//                                .build()
//                );
//                System.out.println("✅ 文件上传成功: " + objectName);
//            }
//
//            // 4. 拼接直链 URL（因为是 public bucket）
//            String publicUrl = String.format("http://localhost:9000/%s/%s", bucketName, objectName);
//            System.out.println("✅ 上传成功，直链地址：");
//            System.out.println(publicUrl);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
