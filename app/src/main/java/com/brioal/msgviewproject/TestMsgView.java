package com.brioal.msgviewproject;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.brioal.msgview.MsgView;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by Brioal on 3/19/2018.
 */

public class TestMsgView extends MsgView {

    public TestMsgView(Context context) {
        super(context);
    }

    public TestMsgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLoadingView() {
        return R.layout.layout_loading;
    }

    @Override
    protected int getLoadFailedView() {
        return R.layout.layout_failed;
    }
}
