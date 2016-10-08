package com.lyx.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyx.demo.R;
import com.lyx.demo.listener.MyItemClickListener;

class MyViewHolder extends RecyclerView.ViewHolder implements MyItemClickListener {
    TextView mTextView;
    ImageView mImg;
    public MyViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_active);
        mImg = (ImageView)itemView.findViewById(R.id.img_active);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
