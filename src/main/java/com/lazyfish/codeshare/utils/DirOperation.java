package com.lazyfish.codeshare.utils;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lazyfish.codeshare.entity.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;



@Component
public class DirOperation {
    @Value("${userProjects.path}")
    String rootPath;

    /**
     * 获取树型信息
     * @param id 代码片段id
     * @return
     */
    @Cacheable(cacheNames = "snippetProject_tree",key = "#id")
    public FileInfo getTree(Integer id) {
        System.out.println(rootPath);
        File file = new File(rootPath+"/code/" + String.valueOf(id));
        FileInfo root = new FileInfo(String.valueOf(id),null,"folder","");
        get(root,file);
        return root;
    }

    /**
     * 获取绝对路径
     * @param id 代码片段id
     * @return
     */
    public String getPath(Integer id){
        return rootPath+"/code/" + id +"/";
    }

    /**
     * 获取 并 返回文件资源
     * @param path
     * @return
     */
    public FileSystemResource getFileSystemResource(String path){
        return  new FileSystemResource(path);
    }
    /**
     * 递归获取树形信息
     * @param father 遍历的父目录节点信息
     * @param file 当前目录下的文件对象
     */
    public void get(FileInfo father,File file){
        String root_file =rootPath+ "/code/";
        File[] files = file.listFiles();
        List<FileInfo> fileInfolist = new LinkedList<>();
        for (File f : files) {
            if (f.isDirectory()) {
                String path = f.getAbsolutePath().substring(root_file.length());
                path = path.replaceAll("\\\\", "/");
                FileInfo temp = new FileInfo(f.getName(),null,"folder",path);
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

    /**
     * 更新文件接口
     * @param path 更新路径
     * @param content 更新内容
     * @return
     */
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

    /**
     * 用于文件更名
     * @param path 文件原路径
     * @param new_path 文件新路径
     * @return
     */
    @CacheEvict(cacheNames = "snippetProject_tree",key = "#path.split('/')[0]")
    public boolean reName(String path,String new_path){
        String root_file =rootPath + "/code/";
        return new File(root_file+path).renameTo(new File(root_file+new_path));
    }

    /**
     * 创建目录
     * @param path
     * @return
     * @throws IOException
     */
    @CacheEvict(cacheNames = "snippetProject_tree",key = "#path.split('/')[0]")
    public boolean newDirectory(String path) throws IOException {
        String root_file =rootPath + "/code/";
        Path temp_path = Paths.get(root_file+path);
        System.out.println(root_file+path);
        Files.createDirectory(temp_path);
        return true;
    }

    /**
     * 创建文件
     * @param path
     * @param name
     * @return
     * @throws IOException
     */
    @CacheEvict(cacheNames = "snippetProject_tree",key = "#path.split('/')[0]")
    public boolean newFile(String path,String name) throws IOException {
        String root_file =rootPath + "/code/";
        Path temp_path = Paths.get(root_file+path+"/"+name);
        Files.createFile(temp_path);
        return true;
    }

    /**
     * 删除文件
     * @param path
     * @return
     * @throws IOException
     */
    @CacheEvict(cacheNames = "snippetProject_tree",key = "#path.split('/')[0]")
    public boolean delFile(String path) throws IOException {
        String root_file =rootPath + "/code/";
        Path temp_path = Paths.get(root_file+path);
        DeleteFile(temp_path);
        return true;
    }
    void DeleteFile(Path path) throws IOException {
        Files.walkFileTree(path,new SimpleFileVisitor<Path>() {
            // 先去遍历删除文件
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                System.out.printf("文件被删除 : %s%n", file);
                return FileVisitResult.CONTINUE;
            }
            // 再去遍历删除目录
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                System.out.printf("文件夹被删除: %s%n", dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * 移动文件或文件夹
     * @param path 文件路径 例如：55/1.txt
     * @param new_path 新文件路径 例如：55/new/1.txt
     */
    @CacheEvict(cacheNames = "snippetProject_tree",key = "#path.split('/')[0]")
    public void moveFile(String path,String new_path){
        String root_file =rootPath + "/code/";
        try {
            Files.move(Paths.get(root_file+path), Paths.get(root_file+new_path), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
