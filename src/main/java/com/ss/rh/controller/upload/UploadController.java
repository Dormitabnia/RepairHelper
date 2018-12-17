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
        if (file.getContentType() != null) {
            String imgType = getImgType(file.getContentType());

            if (imgType.equalsIgnoreCase("error")) {
                logger.warn("图片格式不支持");
                return JsonUtil.failure("图片文件格式不支持");
            }


            System.out.println("接收到图片文件，格式为" + file.getContentType());
            logger.info("接收到图片文件，格式为" + imgType);

            return uploadService.uploadImg(file, imgType).toJSONString();
        }

        logger.warn("文件类型为空，接收失败");
        return JsonUtil.failure("文件类型为空，未接收文件");

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

    /*
    判断图片文件类型
     */
    public static String getImgType(String contentType) {
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
}
