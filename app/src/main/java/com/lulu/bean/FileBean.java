package com.lulu.bean;

/**
 * 文件Bean
 * Created by Lu on 2015/10/28.
 */
public class FileBean {

    private int id;

    private int parentId;

    private String name;

    public FileBean(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
