package com.ss.rh.controller.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @RequestMapping(method = RequestMethod.POST, value = "/img")
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sound")
    public String uploadSound(@RequestParam("file") MultipartFile file) {
        return null;
    }
}
