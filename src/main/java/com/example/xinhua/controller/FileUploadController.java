package com.example.xinhua.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.xinhua.pojo.Result;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("/updateFile")
    public Result<String> updateFile(MultipartFile file) throws Exception {

        long size = file.getSize();
        if (size < 0) {
            return Result.error("上传文件为空");
        }

        String type = file.getContentType();
        if (type == null || !type.startsWith("image")) {
            return Result.error("上传文件类型错误");
        }

        // 文件保存路径
        String filePath = "D:\\upload\\";
        // 文件名
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        // 转存文件
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();

        }
        System.out.println(type);
        if (size > 1024 * 1024 * 10) {
            return Result.error("上传文件不能大于10M");
        }

        String savePacth = "D:\\" + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(savePacth));

        return Result.success("成功", savePacth);
    }

}
