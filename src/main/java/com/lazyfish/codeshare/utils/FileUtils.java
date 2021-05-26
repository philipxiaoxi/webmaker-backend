package com.lazyfish.codeshare.utils;

import java.util.HashMap;
import java.util.regex.Pattern;

public class FileUtils {
        private static final HashMap<String,String> map = new HashMap<>();
        static {
            // 一般网页资源
            map.put("html", "text/html;charset=utf-8");
            map.put("htm", "text/html;charset=utf-8");
            map.put("js", "application/x-javascript;charset=utf-8");
            map.put("css", "text/css;charset=utf-8");
            map.put("txt", "text/plain;charset=utf-8");
            map.put(".otf", "application/x-font-otf");

            // 图片
            map.put("gif", "image/gif");
            map.put("jpg", "image/jpeg");
            map.put("jpeg", "image/jpeg");
            map.put("png", "image/png");
            map.put("ico", "image/x-icon");
            map.put("svg", "image/svg+xml");

        }
    /**
     * 通过文件名获取对应文件类型的Content-Type
     * @param name  文件名
     * @return Content-Type结果
     */
    static public String getContentType(String name) {
        name = getSuffix(name);
        String res = map.get(name);
        return res == null ? "application/octet-stream" : res;
    }
    /**
     * 获取文件名中的后缀名，即最后一个字符'.'后面的子字符串，若无后缀名，则返回整个原文件名
     * @author xiaotao
     * @param name  文件名或文件路径
     * @return  后缀名，不带'.'
     */
    public static String getSuffix(String name) {
        String[] split = name.split("\\.");
        if (split.length != 0) {
            return split[split.length - 1].toLowerCase();
        } else {
            return name;
        }
    }

    /**
     * 判断文件路径是否包含非法路径
     * @param path
     * @throws Exception
     */
    public static void pathTest(String path) throws Exception {
        Pattern pattern = Pattern.compile("(\\\\|/)\\.\\.(\\\\|/)|(\\\\|/)\\.\\.$|^\\.\\.(\\\\|/)");
        if(pattern.matcher(path).find()){
            throw new Exception("非法的路径，不得包含/../或使用/..结尾");
        }
    }
}

