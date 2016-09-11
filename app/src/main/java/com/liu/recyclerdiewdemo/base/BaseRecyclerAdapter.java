package com.liu.recyclerdiewdemo.base;

/**
 * Created by Administrator on 2016/4/25.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter< T > extends RecyclerView.Adapter< BaseRecyclerViewHolder > {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List< T > mDatas = new ArrayList< T >();
    public OnItemClickListener< T > mOnItemClickListener;

    public BaseRecyclerAdapter( Context context, List< T > datas ) {
        mContext = context;
        mInflater = LayoutInflater.from( context );
        if ( datas != null ) {
            mDatas = datas;
        }
    }


    @Override
    public int getItemViewType( int position ) {
        return super.getItemViewType( position );
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if ( mDatas.size() > 0 ) {
            count = mDatas.size();
        }
        return count;
    }


    public void addItemLast( List< T > datas ) {
        mDatas.addAll( datas );
        notifyDataSetChanged();
    }

    public void addItemTop( List< T > datas ) {
        mDatas.addAll( 0, datas );
        notifyDataSetChanged();
    }

    public void remove( int position ) {
        mDatas.remove( position );
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public long getItemId( int position ) {
        return position;
    }

    public List< T > getDatas() {
        return mDatas;
    }

    // 点击事件接口
    public interface OnItemClickListener< T > {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener( OnItemClickListener< T > listener ) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public void onBindViewHolder( BaseRecyclerViewHolder holder, int position ) {
        bindHolder( holder, position );
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        return createHolder( parent, viewType );
    }


    protected abstract void bindHolder( BaseRecyclerViewHolder holder, int position );

    /***
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract BaseRecyclerViewHolder createHolder( ViewGroup parent, int viewType );

}
