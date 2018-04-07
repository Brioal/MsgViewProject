package com.brioal.msgviewproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.TestMethod;
import android.view.View;
import android.widget.Button;

import com.brioal.msgview.MsgView;

public class MainActivity extends AppCompatActivity {

    private TestMsgView mMsgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMsgView = (TestMsgView) findViewById(R.id.main_msgView);
    }

    public void refresh(View view) {
        mMsgView.setLoading().build();
    }

    public void failed(View view) {
        mMsgView.setError("这是错误信息").setReloadListener(new MsgView.OnReloadListener() {
            @Override
            public void reload() {

            }
        }).build();
    }

    public void done(View view) {
        mMsgView.setLoadDone().build();
    }
}
