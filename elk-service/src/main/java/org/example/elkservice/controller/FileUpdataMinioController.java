package org.example.elkservice.controller;

import org.example.elkservice.service.UploadFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class FileUpdataMinioController {

    @Resource
    private UploadFileService uploadFileService;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam(value = "path", defaultValue = "") String path){
        uploadFileService.uploadFile(file, path);
        System.out.println("✅ 文件上传成功: " + file.getOriginalFilename());
    }

}
