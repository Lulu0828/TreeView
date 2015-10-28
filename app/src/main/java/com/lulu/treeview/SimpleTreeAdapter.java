package com.lulu.treeview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lulu.treeview.bean.Node;
import com.lulu.treeview.bean.TreeListViewAdapter;

import java.util.List;

/**
 *
 * Created by Lu on 2015/10/28.
 */
public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T>{

    public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas, int defaultExpandLevel)
            throws IllegalAccessException {
        super(mTree, context, datas, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.icon = (ImageView)convertView.findViewById(R.id.id_treenode_icon);
            viewHolder.label = (TextView)convertView.findViewById(R.id.id_treenode_label);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (node.getIcon() == -1) {
            viewHolder.icon.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.icon.setImageResource(node.getIcon());
        }
        viewHolder.label.setText(node.getName());
        return convertView;
    }

    private static class ViewHolder {
        ImageView icon;
        TextView label;
    }

}
