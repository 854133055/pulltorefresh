package com.pulltorefresh.hank.pulltorefreshdemo;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PullToRefreshListView mLvRefresh;
    private boolean mIsRefreshing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLvRefresh = findViewById(R.id.lv_refresh);
        List<HashMap<String, String>> data = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            HashMap<String, String> map = new HashMap<>();
            map.put("top_text", "top text-" + i);
            map.put("bottom_text", "bottom text-" + i);
            data.add(map);
        }
        // 可设置高度
//        mLvRefresh.getmHeaderLoadingView().setHeight(300);
        // 可设置背景颜色
//        mLvRefresh.getmHeaderLoadingView().setBackgroundColor(Color.RED);
        mLvRefresh.setAdapter(new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, new String[]{"top_text", "bottom_text"}, new int[]{android.R.id.text1, android.R.id.text2}));
        mLvRefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mIsRefreshing){
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLvRefresh.getmHeaderLoadingView().setRefreshingLabel("加载失败");
                    }
                }, 3000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLvRefresh.onRefreshComplete();
                        mIsRefreshing = false;
                    }
                }, 10000);
                mIsRefreshing = true;
            }
        });
    }
}
