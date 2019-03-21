package com.ss.rh.controller.upload;

import com.alibaba.fastjson.JSONObject;
import com.ss.rh.annotation.LoginRequired;
import com.ss.rh.service.UploadService;
import com.ss.rh.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    /*
    单张图片上传
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/img")
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        logger.info("接收到图片文件");
        return uploadService.uploadFile(file, "image").toJSONString();
    }

    /*
    单个声音文件上传
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/sound")
    public String uploadSound(@RequestParam("file") MultipartFile file) {
        logger.info("接收到声音文件");
        return uploadService.uploadFile(file, "sound").toJSONString();
    }

    /*
    多张图片上传
     */
    @LoginRequired
    @RequestMapping(method = RequestMethod.POST, value = "/multiImg")
    public String uploadMultiImg(MultipartRequest multipartRequest) {
        List<MultipartFile> files = multipartRequest.getFiles("file");
        logger.info("开始上传一组图片文件");

        if (files != null && files.size() > 0) {
            List<JSONObject> results = new ArrayList<>();

            for (MultipartFile file : files) {
                results.add(uploadService.uploadFile(file, "image"));
            }
            return JsonUtil.success("上传完成", results);
        }

        return JsonUtil.failure("没有文件数据");
    }

}
