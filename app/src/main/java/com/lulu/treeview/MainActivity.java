package com.lulu.treeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.lulu.bean.FileBean;
import com.lulu.treeview.bean.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<FileBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        ListView tree = (ListView) findViewById(R.id.id_tree);
        try {
            TreeListViewAdapter adapter = new SimpleTreeAdapter<>(tree, this, datas, 0);
            tree.setAdapter(adapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initDatas() {

        datas.add(new FileBean(1, 0, "文件管理系统"));
        datas.add(new FileBean(2, 1, "游戏"));
        datas.add(new FileBean(3, 1, "文档"));
        datas.add(new FileBean(4, 1, "程序"));
        datas.add(new FileBean(5, 2, "war3"));
        datas.add(new FileBean(6, 2, "刀塔传奇"));

        datas.add(new FileBean(7, 4, "面向对象"));
        datas.add(new FileBean(8, 4, "非面向对象"));

        datas.add(new FileBean(9, 7, "C++"));
        datas.add(new FileBean(10, 7, "JAVA"));
        datas.add(new FileBean(11, 7, "Javascript"));
        datas.add(new FileBean(12, 8, "C"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
