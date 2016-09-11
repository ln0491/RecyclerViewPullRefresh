package com.liu.recyclerdiewdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liu.recyclerdiewdemo.R;
import com.liu.recyclerdiewdemo.base.BaseRecyclerAdapter;
import com.liu.recyclerdiewdemo.base.BaseRecyclerViewHolder;

import java.util.List;

/**
 * Created by 刘楠 on 2016/9/11 0011.16:37
 */
public class XRecyclerViewAdapter extends BaseRecyclerAdapter {

    public XRecyclerViewAdapter(Context context, List<String> datas){
        super(context, datas);
    }

    @Override
    protected void bindHolder(BaseRecyclerViewHolder holder, int position) {



       if(holder instanceof  ViewHolder){
           String str = (String) mDatas.get(position);
           ViewHolder viewHolder = (ViewHolder) holder;
           viewHolder.mTvContent.setText(str);
       }

    }

    @Override
    protected BaseRecyclerViewHolder createHolder(ViewGroup parent, int viewType) {
        View itemView  = mInflater.inflate(R.layout.item_listview,parent,false);
        return new ViewHolder(itemView);
    }



    public class ViewHolder extends BaseRecyclerViewHolder{

        private TextView mTvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {

            mTvContent = (TextView) itemView.findViewById(R.id.tvContent);
        }
    }
}
