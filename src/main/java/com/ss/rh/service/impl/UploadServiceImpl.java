package com.ss.rh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ss.rh.service.UploadService;
import com.ss.rh.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {

    @Value("${file.rootFolderName}")
    String rootPath;

    @Value("${file.hostUrl}")
    String hostUrl;

    @Override
    public JSONObject uploadImg(MultipartFile file, String type) {
        // TODO:是否可以将图片上传和声音文件上传统一
        // 使用当前时间创建图片目录以及为图片命名
        Date now = new Date();
        String folderPath = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
            folderPath = format.format(now);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (folderPath != null) {
            String fileName = now.getTime() + "." + type;
            // 写图片文件
            boolean flag = writeImg(file, folderPath, fileName);

            if(!flag)
                return JsonUtil.str2Json(JsonUtil.failure("上传失败"));

            String uri = "image/" + folderPath + fileName;

            return JsonUtil.str2Json(JsonUtil.success("上传成功", uri));
        }

        return JsonUtil.str2Json(JsonUtil.failure("上传失败"));
    }

    @Override
    public JSONObject uploadSound(MultipartFile file, String type) {
        return null;
    }

    public boolean writeImg(MultipartFile file, String folderPath, String fileName) {
        return false;
    }

}
