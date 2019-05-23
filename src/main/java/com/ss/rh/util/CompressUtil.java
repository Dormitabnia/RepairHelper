package com.ss.rh.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/*
进行二维码信息字符串（解）压缩的工具类
 */
@Component
public class CompressUtil {
    public HashMap<String, Integer> table= new HashMap<String, Integer>();
    private String[] Array_char;
    private int count;

    /*
    压缩
     */
    public String LZW_Compress(String input) {
        /*向表中填入256个ASCALL字符*/
        Array_char = new String[4096];
        for (int i = 0; i < 256; i++) {
            table.put(Character.toString((char) i), i);
            Array_char[i] = Character.toString((char) i);
        }
        count = 256;

        char input_char;
        String temp="";
        int point=0;  //用于取字符串中的每个字符
        StringBuffer resultBuffer=new StringBuffer("");

        input_char=input.charAt(point++);
        temp=""+input_char;
        char c;

        while (point<input.length()) {
            input_char=input.charAt(point++);
            if (table.containsKey(temp+input_char))
            {
                temp=temp+input_char;
            }
            else {
                int temp1=(int)table.get(temp);
                resultBuffer.append((char)temp1);
                table.put(temp + input_char, count++);
                temp=""+input_char;
            }
        }

        resultBuffer.append(temp);

        return resultBuffer.toString();

    }

    /*
    解压缩
     */
    public String lZW_Decompress(String input) {
        Array_char = new String[4096];
        for (int i = 0; i < 256; i++) {
            table.put(Character.toString((char) i), i);
            Array_char[i] = Character.toString((char) i);
        }
        count = 256;

        String result = "";
        int pro, las;
        int point = 0;

        pro=input.charAt(point++);
        result = result+Array_char[pro];

        while (point<input.length()) {
            las = input.charAt(point++);

            if (las>=count) {   //不存在
                Array_char[count] = Array_char[pro] + Array_char[pro].charAt(0);
                result = result + Array_char[pro] + Array_char[pro].charAt(0);

            }
            else {
                Array_char[count] = Array_char[pro] + Array_char[las].charAt(0);
                result = result + Array_char[las];
            }
            count++;
            pro = las;
        }
        return result;
    }
}
