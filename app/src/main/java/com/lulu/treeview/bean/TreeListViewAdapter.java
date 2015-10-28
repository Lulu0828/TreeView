package com.lulu.treeview.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 *
 * Created by Lu on 2015/10/28.
 */
public abstract class TreeListViewAdapter<T> extends BaseAdapter {

    protected Context context;

    protected LayoutInflater inflater;

    /**
     * 存储所有可见的Node
     */
    protected List<Node> nodes;

    /**
     * 存储所有的Node
     */
    protected List<Node> allNodes;

    /**
     * 点击的回调接口
     */
    private OnTreeNodeClickListener onTreeNodeClickListener;

    public TreeListViewAdapter(ListView mTree, Context context, List<T> datas,
                               int defaultExpandLevel) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        // 对所有的Node进行排序

        // 过滤出可见的Node


        // 设置节点点击时，可以展开以及关闭
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);

                if (onTreeNodeClickListener != null) {
                    onTreeNodeClickListener.onClick(nodes.get(position), position);
                }
            }
        });
    }

    // 展开或关闭某节点
    private void expandOrCollapse(int position) {
        Node node = nodes.get(position);
        if (node != null) {
            if (!node.isLeaf()) {
                node.setExpand(!node.isExpand());
                // 过滤出所有可见的Node


                // 刷新视图
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getCount() {
        return nodes.size();
    }

    @Override
    public Object getItem(int position) {
        return nodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = nodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position,
                                        View convertView, ViewGroup parent);

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

}
