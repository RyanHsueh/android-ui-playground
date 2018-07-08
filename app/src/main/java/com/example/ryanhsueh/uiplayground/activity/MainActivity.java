package com.example.ryanhsueh.uiplayground.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ryanhsueh.uiplayground.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTitleBarActivity(View view) {
        TitleBarActivity.actionStart(this);
    }
    public void startListViewActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ListViewActivity.class);
        startActivity(intent);
    }
    public void startRecycleViewActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, RecycleViewActivity.class);
        startActivity(intent);
    }
    public void startWaterFallRecyclerActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, WaterFallRecyclerActivity.class);
        startActivity(intent);
    }
    public void startChatChatActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ChatChatActivity.class);
        startActivity(intent);
    }
    public void startSimpleFragmentActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SimpleFragmentActivity.class);
        startActivity(intent);
    }
    public void startNewsFragmentActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, NewsFragmentActivity.class);
        startActivity(intent);
    }
    public void startServiceTryActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ServiceTryActivity.class);
        startActivity(intent);
    }
}
