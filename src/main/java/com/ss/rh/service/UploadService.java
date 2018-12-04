package com.ss.rh.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    JSONObject uploadImg(MultipartFile file, String type);

    JSONObject uploadSound(MultipartFile file, String type);

}
