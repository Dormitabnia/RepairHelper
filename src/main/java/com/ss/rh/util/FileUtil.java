package com.ss.rh.util;

import com.ss.rh.constants.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/*
文件写入工具类
 */
@Component
public class FileUtil {

    @Autowired
    ConfigProperties configProperties;

    /*
    写文件
     */
    public boolean writeFile(MultipartFile file, String folderPath, String fileName) {
        try {
            File fd = new File(configProperties.getRootPath() + folderPath);
//            System.out.println(configProperties.getRootPath() + folderPath);
            if (!fd.exists()) {
                fd.mkdirs();
            }

            InputStream in = file.getInputStream();
            OutputStream out = new BufferedOutputStream(
                    new FileOutputStream(configProperties.getRootPath() + folderPath + "/" + fileName));
            byte[] buf = new byte[4096];
            int read;
            while ((read = in.read(buf)) != -1) {
                out.write(buf, 0, read);
            }
            out.close();
            in.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
