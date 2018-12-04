package com.ss.rh.controller.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@RestController
@RequestMapping("/upload")
public class UploadController {

    /*
    单张图片上传
     */
    @RequestMapping(method = RequestMethod.POST, value = "/img")
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        return null;
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
