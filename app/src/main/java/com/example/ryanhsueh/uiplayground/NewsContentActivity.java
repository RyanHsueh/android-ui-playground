package com.example.ryanhsueh.uiplayground;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ryanhsueh.uiplayground.data.News;
import com.example.ryanhsueh.uiplayground.fragment.NewsContentFragment;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(Context context, News news) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", news.getTitle());
        intent.putExtra("news_content", news.getContent());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        Intent intent = getIntent();

        NewsContentFragment fragment =
                (NewsContentFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_news_content);
        fragment.refresh(
                intent.getStringExtra("news_title"),
                intent.getStringExtra("news_content"));
    }
}
