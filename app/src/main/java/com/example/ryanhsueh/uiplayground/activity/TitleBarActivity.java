package com.example.ryanhsueh.uiplayground.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ryanhsueh.uiplayground.ITitleBarAction;
import com.example.ryanhsueh.uiplayground.MyTitleBar;
import com.example.ryanhsueh.uiplayground.R;

public class TitleBarActivity extends NoActionBarActivity implements ITitleBarAction {
    private static final String TAG = TitleBarActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_bar);

        MyTitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setTitle("TitleBarActivity");
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TitleBarActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackClicked() {
        Log.d(TAG, "onBackClicked: " + this);
    }

    @Override
    public void onEditClicked() {
        Log.d(TAG, "onEditClicked: " + this);
    }
}
