package com.liu.recyclerdiewdemo.xrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liu.recyclerdiewdemo.R;
import com.liu.recyclerdiewdemo.indicator.AVLoadingIndicatorView;


public class LoadingMoreFooter extends LinearLayout {

    private SimpleViewSwithcer progressCon;
    private Context mContext;
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private TextView mText;
    private int currentState;
    private XRecyclerView xRecyclerView;
//    public void setXRecyclerView(){
//
//    }
	public LoadingMoreFooter(Context context, XRecyclerView xRecyclerView) {
		super(context);
        this.xRecyclerView=xRecyclerView;
		initView(context);


	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LoadingMoreFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
    public void initView(Context context ){
        currentState=STATE_COMPLETE;
        mContext = context;
        View footerView= LayoutInflater.from(context).inflate(R.layout.listview_footer,null);

        setGravity(Gravity.CENTER);
        progressCon = (SimpleViewSwithcer) footerView.findViewById(R.id.simpleViewSwithcer);

        AVLoadingIndicatorView progressView = new AVLoadingIndicatorView(this.getContext());
        progressView.setIndicatorColor(0xffB5B5B5);
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader);
        progressCon.setView(progressView);
        mText= (TextView) footerView.findViewById(R.id.text_footer_view);
        mText.setText(getResources().getString(R.string.listview_load_done));
        mText.setGravity(Gravity.CENTER_VERTICAL);
        progressCon.setVisibility(View.GONE);
        addView(footerView);

        LayoutParams params= new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentState==STATE_COMPLETE){
                    XRecyclerView.LoadingListener loadingListener=xRecyclerView.getLoadingListener();
                    if (loadingListener!=null){
//                        loadingListener.onLoadMore();
                        xRecyclerView.loadMoreData();
                    }
                }
            }
        });
    }

    public void setProgressStyle(int style) {
        if(style == ProgressStyle.SysProgress){
            progressCon.setView(new ProgressBar(mContext, null, android.R.attr.progressBarStyle));
        }else{
            AVLoadingIndicatorView progressView = new  AVLoadingIndicatorView(this.getContext());
            progressView.setIndicatorColor(0xffB5B5B5);
            progressView.setIndicatorId(style);
            progressCon.setView(progressView);
        }
    }

    public void  setState(int state) {
        switch(state) {
            case STATE_LOADING:
                currentState=STATE_LOADING;
                progressCon.setVisibility(View.VISIBLE);
                mText.setText(mContext.getText(R.string.listview_loading));
                progressCon.setVisibility(View.VISIBLE);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                currentState=STATE_COMPLETE;
                mText.setText(mContext.getText(R.string.listview_load_done));
                progressCon.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_NOMORE:
                currentState=STATE_NOMORE;
                mText.setText(mContext.getText(R.string.nomore_loading));
                progressCon.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }

    }
}
