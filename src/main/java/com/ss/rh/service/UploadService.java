package com.ss.rh.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    JSONObject uploadFile(MultipartFile file, String type);

}
