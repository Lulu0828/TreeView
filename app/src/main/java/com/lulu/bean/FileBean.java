package com.lulu.bean;

import com.lulu.treeview.bean.TreeNodeId;
import com.lulu.treeview.bean.TreeNodeLabel;
import com.lulu.treeview.bean.TreeNodePid;

/**
 * 文件Bean
 * Created by Lu on 2015/10/28.
 */
public class FileBean {

    @TreeNodeId
    private int id;
    @TreeNodePid
    private int parentId;
    @TreeNodeLabel
    private String name;

    public FileBean(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
