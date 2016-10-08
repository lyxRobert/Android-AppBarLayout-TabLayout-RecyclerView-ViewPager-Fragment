package com.lyx.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.lyx.demo.adapter.MyRecyclerViewAdapter;
import com.lyx.demo.R;
import com.lyx.demo.listener.MyItemClickListener;
import com.lyx.demo.view.LoadMoreRecyclerView;

public class FragmentPopularity extends Fragment {
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static FragmentPopularity instance=null;
    public static FragmentPopularity newInstance() {
        if(instance==null){
            instance= new FragmentPopularity();
        }
        return instance;
    }
    public FragmentPopularity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout, container, false);
        final LoadMoreRecyclerView mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        int[]mImgList = new int[]{R.drawable.g1,R.drawable.g2,R.drawable.g3,R.drawable.g4,R.drawable.g5,R.drawable.g6,R.drawable.g7,R.drawable.g8,R.drawable.g9,R.drawable.g10};
        String [] mTag = getActivity().getResources().getStringArray(R.array.test);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),mImgList,mTag);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getActivity(),"test"+postion,Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAutoLoadMoreEnable(true);
        mRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"加载更多",Toast.LENGTH_SHORT).show();
                        //TODO 下面注释掉的代码中的false是当数据加载完成以后为false的，然后加载更多的条目就会隐藏。
//                        mRecyclerView.notifyMoreFinish(false);
                    }
                }, 1000);
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
