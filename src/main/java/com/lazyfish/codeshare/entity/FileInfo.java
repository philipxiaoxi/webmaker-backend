package com.lazyfish.codeshare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class FileInfo {
    private String label;
    private List<FileInfo> children;
    private String type;
    private String path;

    public FileInfo() {
    }
}
