package com.lazyfish.codeshare.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;


class FileInfo {
    private String label;
    private List<FileInfo> children;
    FileInfo(String label, List<FileInfo> children) {
        this.label = label;
        this.children = children;
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
}

public class DirOperation {
    public static void main(String[] args) throws IOException {
        System.out.println("测试");
        getDirTree("D:\\codeshare_res\\code\\55");
    }

    public static void getDirTree(String path) throws IOException {
        HashMap<Path, FileInfo> res = new HashMap<>();
        res.put(Paths.get(path), null);
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                while (res.get(dir) == null) {
                    dir = dir.getParent();
                }
                return super.preVisitDirectory(dir, attrs);
            }
        });

    }
}