package com.ss.rh.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

//    public static boolean writeFile() {
//        return false;
//    }

    /*
    写文件
     */
    public static boolean writeFile(MultipartFile file, String folderPath, String fileName) {
        try {
            File fd = new File(folderPath);  // TODO:真实路径还需要考虑
            if (!fd.exists()) {
                fd.mkdirs();
            }

            InputStream in = file.getInputStream();
            OutputStream out = null;
            out = new BufferedOutputStream(new FileOutputStream(folderPath + "/" + fileName));
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
