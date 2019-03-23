package com.ss.rh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ss.rh.service.UploadService;
import com.ss.rh.util.FileUtil;
import com.ss.rh.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    FileUtil fileUtil;

    @Override
    public JSONObject uploadFile(MultipartFile file, String type) {
        if (file.getContentType() != null) {
            // 判断文件类型
            String fileType = null;

            if (type.equalsIgnoreCase("image"))
                fileType = getImgType(file.getContentType());
            else if (type.equalsIgnoreCase("sound"))
                fileType = getSoundType(file.getContentType());

            if (fileType != null && fileType.equalsIgnoreCase("error")) {
                return JsonUtil.str2Json(JsonUtil.failure("文件格式不支持"));
            }

            logger.info("接收到"+ type + "文件，格式为" + file.getContentType());

            // 使用当前时间创建目录以及为文件命名
            Date now = new Date();
            String folderPath = getFolderPath(now);

            if (folderPath != null) {
                String fileName = now.getTime() + "." + fileType;
                // 写文件
                boolean flag = fileUtil.writeFile(file, type + "/" + folderPath, fileName);

                if(!flag)
                    return JsonUtil.str2Json(JsonUtil.failure("上传失败"));

                String uri = type + "/" + folderPath + fileName;

                logger.info("文件写入成功");
                return JsonUtil.str2Json(JsonUtil.success("上传成功", uri));
            }

            logger.error("目录创建失败！");

        }

        return JsonUtil.str2Json(JsonUtil.failure("文件类型为空！"));
    }

    /*
    根据系统时间生成保存路径
     */
    private String getFolderPath(Date now) {
        String folderPath = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
            folderPath = format.format(now);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return folderPath;
    }

    /*
    获取图片文件类型
     */
    private String getImgType(String contentType) {
        if (contentType.contains("png"))
            return "png";
        else if (contentType.contains("jp"))
            return "jpg";
        else if (contentType.contains("bmp"))
            return "bmp";
        else if (contentType.contains("gif"))
            return "gif";
        else
            return "error";
    }

    /*
    获取声音文件类型
     */
    private String getSoundType(String contentType) {
        logger.info(contentType);
        if (contentType.contains("mp3"))
            return "mp3";
        else if (contentType.contains("wav"))
            return "wav";
        else
            return "error";
    }

}
