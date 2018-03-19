package com.brioal.msgview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 3/19/2018.
 */

public class ArticleLoadingView extends View {
    private int mWith;
    private int mHeight;
    // 背景颜色
    private int mColorBack = Color.WHITE;
    // 前景颜色
    private int mColorFront;
    // 段落高度
    private int mSectionHeight = 27;
    private int mSectionHeightPX;
    // 段落的数量
    private int mSectionCount;
    // 绘制的边界
    private int mSectionMagin = 20;
    private int mSectionMaginPX;
    // 总布局的边界
    private int mBoundMagin = 30;
    private int mBoundMaginPX;
    // 绘制的画笔
    private Paint mPaint;
    // 渐进的步长
    private int mStep = 50;

    private Context mContext;
    LinearGradient mLinearGradient;

    public ArticleLoadingView(Context context) {
        this(context, null);
    }

    public ArticleLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mColorFront = Color.parseColor("#f1f1f1");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mColorFront);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWith = w;
        mHeight = h;
        // 计算能显示几个段落
        mSectionHeightPX = DpToPx(mContext, mSectionHeight);
        mSectionMaginPX = DpToPx(mContext, mSectionMagin);
        mBoundMaginPX = DpToPx(mContext, mBoundMagin);
        mSectionCount = (mHeight - 2 * mBoundMaginPX) / (mSectionHeightPX + mSectionMaginPX);

        mLinearGradient = new LinearGradient(0, 0, getScreenWidth(mContext) - mBoundMaginPX * 2, 0,
                new int[]{Color.parseColor("#FFF1F1F1"), Color.parseColor("#FFE9E9E9"), Color.parseColor("#FFF1F1F1")}, null, Shader.TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        mGradientMatrix = new Matrix();
    }

    private Matrix mGradientMatrix;
    private int translateX = 0;
    private int lastDirection = mStep;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置背景
        canvas.drawColor(mColorBack);
        // 开始的x
        // 绘制段落
        for (int i = 0; i < mSectionCount; i++) {
            int endx = 0;
            if (i == 1) {
                endx = getScreenWidth(mContext) / 2;
            } else {
                endx = getScreenWidth(mContext) - mBoundMaginPX;
            }
            // 绘制
            mPaint.setColor(mColorFront);
            Rect rect = new Rect(mBoundMaginPX, mBoundMaginPX + (mSectionHeightPX + mSectionMaginPX) * i, endx, mBoundMaginPX + (mSectionHeightPX + mSectionMaginPX) * i + mSectionHeightPX);
            canvas.drawRect(rect, mPaint);
        }
        translateX += mStep;
        if (translateX > getScreenWidth(mContext)) {
            translateX = 0;
        }
        mGradientMatrix.setTranslate(translateX, 0);
        mLinearGradient.setLocalMatrix(mGradientMatrix);
        postInvalidateDelayed(50);
    }

    /**
     * DP 转Px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int DpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * SP 转Px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int SpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

}
