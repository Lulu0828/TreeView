package com.lulu.treeview.bean;

import com.lulu.treeview.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 树节点处理工具
 * Created by Lu on 2015/10/28.
 */
public class TreeHelper {

    // 传入普通bean，转化为排序后的Node
    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel)
            throws IllegalAccessException {
        List<Node> result = new ArrayList<>();
        // 将用户数据转化为List<Node>以及设置Node间关系
        List<Node> nodes = convetData2Node(datas);
        // 拿到根节点
        List<Node> rootNodes = getRootNodes(nodes);
        // 排序
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    // 把一个节点上的所有的内容都挂上去
    private static void addNode(List<Node> nodes, Node node, int defaultExpandLevel,
                                int currentLevel) {
        nodes.add(node);

        if (defaultExpandLevel >= currentLevel) {
            node.setExpand(true);
        }

        if (node.isLeaf())
            return;
        for (int i = 0; i < node.getChildren().size(); i ++) {
            addNode(nodes, node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);
        }
    }

    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot())
                root.add(node);
        }
        return root;
    }

    // 将数据对应转换为树节点
    private static <T> List<Node> convetData2Node(List<T> datas) throws IllegalAccessException {
        List<Node> nodes = new ArrayList<>();
        Node node;

        for (T t : datas) {
            int id = -1;
            int pId = -1;
            String label = null;

            Class<?> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f : declaredFields) {
                if (f.getAnnotation(TreeNodeId.class) != null) {
                    f.setAccessible(true);
                    id = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodePid.class) != null) {
                    f.setAccessible(true);
                    pId = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodeLabel.class) != null) {
                    f.setAccessible(true);
                    label = (String) f.get(t);
                }
                if (id != -1 && pId != -1 && label != null)
                    break;
            }

            node = new Node(id, pId, label);
            nodes.add(node);
        }

        // 设置Node间，父子关系;让每两个节点都比较一次，即可设置其中的关系
        for (int i = 0; i < nodes.size(); i ++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j ++) {
                Node m = nodes.get(j);
                if (m.getpId() == n.getId()) {
                    n.getChildren().add(m);
                    m.setParent(n);
                }
                if (m.getId() == n.getpId()) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }

        // 设置图片
        for (Node n : nodes) {
            setNodeIcon(n);
        }

        return nodes;
    }

    // 过滤出所有可见的Node
    public static List<Node> filterVisibleNode(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            // 如果为跟节点，或者上层目录为展开状态
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    private static void setNodeIcon(Node node) {
        if (!node.isLeaf() && node.isExpand()) {
            node.setIcon(R.drawable.tree_ex);
        } else if (!node.isLeaf()) {
            node.setIcon(R.drawable.tree_ec);
        } else {
            node.setIcon(-1);
        }
    }

}
