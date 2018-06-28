package com.example.ryanhsueh.uiplayground;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
