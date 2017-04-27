package com.brioal.msgviewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.brioal.msgview.MsgView;

public class MainActivity extends AppCompatActivity {

    private MsgView mMsgView;
    private Button mBtnLoad;
    private Button mBtnFailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMsgView = (MsgView) findViewById(R.id.main_msgView);
        mBtnLoad = (Button) findViewById(R.id.main_btn_refresh);
        mBtnFailed = (Button) findViewById(R.id.main_btn_failed);
        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMsgView.setText("加载中").setStatue(MsgView.STATUE_LOADING).setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher)).build();
            }
        });
        mBtnFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMsgView.setText("加载失败,点击重试").setStatue(MsgView.STATUE_FAILED).setReloadListener(new MsgView.OnReloadListener() {
                    @Override
                    public void reload() {
                        mMsgView.setText("加载中").setStatue(MsgView.STATUE_LOADING).build();
                    }
                }).build();
            }
        });
    }
}
