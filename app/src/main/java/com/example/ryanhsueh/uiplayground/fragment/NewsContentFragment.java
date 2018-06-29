package com.example.ryanhsueh.uiplayground.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryanhsueh.uiplayground.R;

public class NewsContentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news_content,container,false);
        return view;
    }

    public void refresh(String title, String content) {
        TextView textTitle = getView().findViewById(R.id.text_news_title);
        textTitle.setText(title);

        TextView textContent = getView().findViewById(R.id.text_news_content);
        textContent.setText(content);
    }

}
