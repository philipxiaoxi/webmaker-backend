package com.lazyfish.codeshare.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
class FileInfo {
    private String label;
    private List<FileInfo> children;
    private String type;
    private String path;
    FileInfo(String label, List<FileInfo> children,String type,String path) {
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

    public List<FileInfo> getChildren() {
        return children;
    }

    public void setChildren(List<FileInfo> children) {
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


@Component
public class DirOperation {
    @Value("${userProjects.path}")
    String rootPath;
    public FileInfo getTree(Integer id) {
        System.out.println(rootPath);
        File file = new File(rootPath+"/code/" + String.valueOf(id));
        FileInfo root = new FileInfo(String.valueOf(id),null,"folder","");
        get(root,file);
        return root;
    }
    public String getPath(Integer id){
        return rootPath+"/code/" + id +"/";
    }
    public void get(FileInfo father,File file){
        String root_file =rootPath+ "/code/";
        File[] files = file.listFiles();
        List<FileInfo> fileInfolist = new LinkedList<>();
        for (File f : files) {
            if (f.isDirectory()) {
                FileInfo temp = new FileInfo(f.getName(),null,"folder","");
                fileInfolist.add(temp);
                get(temp,new File(f.getAbsolutePath()));
            } else {
                String path = f.getAbsolutePath().substring(root_file.length());
                path = path.replaceAll("\\\\", "/");
                FileInfo temp = new FileInfo(f.getName(),null,"file",path);
                fileInfolist.add(temp);
            }
            father.setChildren(fileInfolist);
        }
    }
    public String update(String path,String content){
        String root_file =rootPath + "/code/";
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
