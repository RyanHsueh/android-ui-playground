package com.example.ryanhsueh.uiplayground;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyTitleBar extends LinearLayout {

    private ITitleBarAction mTitleBarAction;

    public MyTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar, this);

        mTitleBarAction = (ITitleBarAction) context;

        findViewById(R.id.btn_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
                mTitleBarAction.onBackClicked();
            }
        });

        findViewById(R.id.btn_edit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "What do you want to edit", Toast.LENGTH_SHORT).show();
                mTitleBarAction.onEditClicked();
            }
        });
    }

    public void setTitle(String title) {
        TextView textView = findViewById(R.id.text_title);
        textView.setText(title);
    }

}
