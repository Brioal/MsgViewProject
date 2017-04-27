package com.brioal.msgview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
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

public class MsgView extends LinearLayout {
    public static final int STATUE_LOADING = 0;//加载状态
    public static final int STATUE_FAILED = 1;//失败状态
    public static final int STATUE_DONE = 2;//成功状态

    private int mStatue = STATUE_LOADING;//当前状态
    private String mText = "";//要显示的文字
    private Context mContext;
    private ImageView mImageView;
    private TextView mTextView;
    private Drawable mLoadingDrawable;//加载的画面
    private Drawable mErrorDrawable;//失败的动画
    private OnClickListener mClickListener;

    public MsgView(Context context) {
        this(context, null);
    }

    public MsgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public MsgView setLoadingDrawable(Drawable loadingDrawable) {
        mLoadingDrawable = loadingDrawable;
        return this;
    }

    public MsgView setErrorDrawable(Drawable errorDrawable) {
        mErrorDrawable = errorDrawable;
        return this;
    }

    public MsgView setStatue(int statue) {
        mStatue = statue;
        return this;
    }

    public MsgView setText(String text) {
        mText = text;
        return this;
    }

    public MsgView setClickListener(OnClickListener clickListener) {
        mClickListener = clickListener;
        return this;
    }

    private void init() {
        mImageView = new ImageView(mContext);
        mTextView = new TextView(mContext);
        mLoadingDrawable = mContext.getResources().getDrawable(R.drawable.ic_refresh_green);
        mErrorDrawable = mContext.getResources().getDrawable(R.drawable.ic_error_red);
    }

    public void build() {
        mImageView.clearAnimation();
        removeAllViews();
        setGravity(Gravity.CENTER);
        setBackgroundColor(Color.WHITE);
        setOrientation(LinearLayout.VERTICAL);
        //添加图片
        LayoutParams paramsImageView = new LayoutParams(SizeUtil.DpToPx(mContext, 60), SizeUtil.DpToPx(mContext, 60));
        paramsImageView.leftMargin = SizeUtil.DpToPx(mContext, 15);
        paramsImageView.topMargin = SizeUtil.DpToPx(mContext, 15);
        paramsImageView.rightMargin = SizeUtil.DpToPx(mContext, 15);
        paramsImageView.bottomMargin = SizeUtil.DpToPx(mContext, 15);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        switch (mStatue) {
            case STATUE_LOADING:
                mImageView.setImageDrawable(mLoadingDrawable);
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotating);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (mStatue == STATUE_LOADING) {
                            mImageView.startAnimation(animation);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                animation.setDuration(900);
                mImageView.startAnimation(animation);
                break;
            case STATUE_FAILED:
                mImageView.setImageDrawable(mErrorDrawable);
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.click();
                    }
                });
                break;
        }
        addView(mImageView, paramsImageView);
        //添加文字
        LayoutParams paramsTextView = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsTextView.leftMargin = SizeUtil.DpToPx(mContext, 25);
        paramsTextView.topMargin = SizeUtil.DpToPx(mContext, 25);
        paramsTextView.rightMargin = SizeUtil.DpToPx(mContext, 25);
        paramsTextView.bottomMargin = SizeUtil.DpToPx(mContext, 25);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setText(mText);
        mTextView.setTextSize(SizeUtil.SpToPx(mContext, 7));
        mTextView.setGravity(Gravity.CENTER);
        addView(mTextView, paramsTextView);
    }

    public interface OnClickListener {
        void click();
    }
}
