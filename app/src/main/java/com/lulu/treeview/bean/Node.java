package com.lulu.treeview.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 * Created by Lu on 2015/10/28.
 */
public class Node {

    private int id;

    /**
     * 父节点ID，根节点的pId为0
     */
    private int pId = 0;

    private String name;

    /**
     * 当前的级别
     */
//    private int level;

    /**
     * 是否展开
     */
    private boolean isExpand = false;

    /**
     * 图标
     */
    private int icon;

    /**
     * 下一级的子Node
     */
    private List<Node> children = new ArrayList<>();

    /**
     * 父Node
     */
    private Node parent;

    public Node() {
    }

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

//    public void setLevel(int level) {
//        this.level = level;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    // 判断是否是根节点
    public boolean isRoot() {
        return parent == null;
    }

    // 判断父节点是否展开
    public boolean isParentExpand() {
        if (isRoot())
            return false;

        return parent.isExpand();
    }

    // 判断是否是叶子节点
    public boolean isLeaf() {
        return children.size() == 0;
    }

    // 设置展开
    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;

        if (!isExpand) {
            for (Node node : children) {
                node.setExpand(isExpand);
            }
        }
    }
}
