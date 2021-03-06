package com.lazyfish.codeshare.utils;

import java.util.Base64;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class SnippetImage {
    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr, String imgFilePath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        imgStr = imgStr.split(",")[1];
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            //Base64解码
            byte[] b =  decoder.decode(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
