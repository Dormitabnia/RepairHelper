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
        //使用当前时间创建图片目录以及为图片命名
        Date now = new Date();
        String nowStr = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            nowStr = format.format(now);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (nowStr != null) {
            String fileName = now.getTime() + "." + type;
            boolean flag = writeImg(file, nowStr, fileName);

            if(!flag)
                return JsonUtil.str2Json(JsonUtil.failure("上传失败"));

            String url = hostUrl + rootPath + fileName;

            return JsonUtil.str2Json(JsonUtil.success("上传成功", url));
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
