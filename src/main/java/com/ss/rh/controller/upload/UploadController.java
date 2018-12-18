package com.ss.rh.controller.upload;

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

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    /*
    单张图片上传
     */
    @RequestMapping(method = RequestMethod.POST, value = "/img")
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        logger.info("接收到文件");
        return uploadService.uploadFile(file, "image").toJSONString();
    }

    /*
    单个声音文件上传
     */
    @RequestMapping(method = RequestMethod.POST, value = "/sound")
    public String uploadSound(@RequestParam("file") MultipartFile file) {
        return null;
    }

    /*
    多张图片上传
     */
    @RequestMapping(method = RequestMethod.POST, value = "/multiImg")
    public String uploadMultiImg(MultipartRequest multipartRequest) {
        return null;
    }

}
