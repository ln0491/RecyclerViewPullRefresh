package com.liu.recyclerdiewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.liu.recyclerdiewdemo.adapter.XRecyclerViewAdapter;
import com.liu.recyclerdiewdemo.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView mXRecyclerView;

    private List<String> mDatas = new ArrayList<>();
    private XRecyclerViewAdapter mXRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }


    private void initView() {

        mXRecyclerView = (XRecyclerView) findViewById(R.id.xRecyclerView);

    }


    private void initData() {

        for (int i = 0; i < 10; i++) {
            mDatas.add("Item " + i);
        }


        initRecyclerView();

    }

    private void initRecyclerView() {

        mXRecyclerViewAdapter = new XRecyclerViewAdapter(this, mDatas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        mXRecyclerView.setLayoutManager(linearLayoutManager);


        mXRecyclerView.setAdapter(mXRecyclerViewAdapter);


    }

    private void initListener() {

        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> headerList = new ArrayList<String>();
                        for (int i = 0; i < 10; i++) {
                            headerList.add("Header Item " + i);
                        }

                        mXRecyclerViewAdapter.addItemTop(headerList);
                        mXRecyclerView.refreshComplete();
                        Toast.makeText(MainActivity.this, "更新了 " + headerList.size() + " 头部信息", Toast.LENGTH_SHORT).show();

                    }
                }, 3000);


            }

            @Override
            public void onLoadMore() {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        List<String> footerList = new ArrayList<String>();
                        for (int i = 0; i < 10; i++) {
                            footerList.add("Footer Item " + i);
                        }

                        mXRecyclerView.loadMoreComplete();
                        mXRecyclerViewAdapter.addItemTop(footerList);
                        Toast.makeText(MainActivity.this, "更新了 " + footerList.size() + " 尾部信息", Toast.LENGTH_SHORT).show();

                    }
                }, 3000);


            }
        });
    }
}
