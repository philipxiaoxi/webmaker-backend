package com.lazyfish.codeshare.utils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class FileInfo2 {
    private String label;
    private List<FileInfo2> children;
    private String type;
    private String path;
    FileInfo2(String label, List<FileInfo2> children,String type,String path) {
        this.label = label;
        this.children = children;
        this.type = type;
        this.path = path;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<FileInfo2> getChildren() {
        return children;
    }

    public void setChildren(List<FileInfo2> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}



public class DirOperation2 {

    public static FileInfo2 getTree(Integer id) {
        File file = new File("D:/codeshare_res/code/" + String.valueOf(id));
        FileInfo2 root = new FileInfo2(String.valueOf(id),null,"folder","");
        get(root,file);
        return root;
    }
    public static  String getPath(Integer id){
        return "D:/codeshare_res/code/" + String.valueOf(id) +"/";
    }
    public static void get(FileInfo2 father,File file){
        String root_file = "D:/codeshare_res/code/";
        File[] files = file.listFiles();
        List<FileInfo2> fileInfolist = new LinkedList<>();
        for (File f : files) {
            if (f.isDirectory()) {
                FileInfo2 temp = new FileInfo2(f.getName(),null,"folder","");
                fileInfolist.add(temp);
                get(temp,new File(f.getAbsolutePath()));
            } else {
                String path = f.getAbsolutePath().substring(root_file.length());
                path = path.replaceAll("\\\\", "/");
                FileInfo2 temp = new FileInfo2(f.getName(),null,"file",path);
                fileInfolist.add(temp);
            }
            father.setChildren(fileInfolist);
        }
    }
    public static String update(String path,String content){
        String root_file = "D:/codeshare_res/code/";
        File f = new File(root_file +path);
        //字节输出流
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            fos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                    return "sucess";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "error";
    }
}
