package com.brioal.msgview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2017/4/22.
 */

public abstract class MsgView extends LinearLayout {

    // 是否是加载模式
    private boolean isLoading;
    // 是否是失败模式
    private boolean isError;
    // 是否加载完成
    private boolean isLoadDone;
    // 加载的View
    private View mLoadingView;
    // 错误的View
    private View mErrorView;
    // 重试监听器
    private OnReloadListener mReloadListener;

    private Context mContext;

    public MsgView(Context context) {
        this(context, null);
    }

    public MsgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    /**
     * 返回默认的article加载进度
     * @return
     */
    public int getDefaultArticleLoadingView() {
        return R.layout.layout_loading_article;
    }

    /**
     * 返回正在加载的布局文件
     *
     * @return
     */
    protected abstract int getLoadingView();

    /**
     * 返回加载失败的View
     *
     * @return
     */
    protected abstract int getLoadFailedView();

    /**
     * @return
     */
    public MsgView setError() {
        isError = true;
        isLoadDone = false;
        isLoading = false;
        return this;
    }

    /**
     * 设置加载完成
     */
    public MsgView setLoadDone() {
        isError = false;
        isLoadDone = true;
        isLoading = false;
        return this;
    }

    /**
     * 设置正在加载
     *
     * @return
     */
    public MsgView setLoading() {
        isError = false;
        isLoadDone = false;
        isLoading = true;
        return this;
    }


    /**
     * 设置重试监听器
     *
     * @param reloadListener
     * @return
     */
    public MsgView setReloadListener(OnReloadListener reloadListener) {
        mReloadListener = reloadListener;
        return this;
    }

    public void build() {
        // 获取View
        mLoadingView = LayoutInflater.from(mContext).inflate(getLoadingView(), this, false);
        mErrorView = LayoutInflater.from(mContext).inflate(getLoadFailedView(), this, false);
        if (mLoadingView == null) {
            return;
        }
        if (mErrorView == null) {
            return;
        }
        removeAllViews();
        if (isLoading) {
            // 正在加载
            setVisibility(VISIBLE);
            addView(mLoadingView);
        } else if ((isError)) {
            // 加载失败
            setVisibility(VISIBLE);
            addView(mErrorView);
            mErrorView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mReloadListener != null) {
                        mReloadListener.reload();
                        setLoading();
                        build();
                    }
                }
            });
        } else {
            // 加载完成
            setVisibility(GONE);
        }

    }

    public interface OnReloadListener {
        void reload();
    }
}
