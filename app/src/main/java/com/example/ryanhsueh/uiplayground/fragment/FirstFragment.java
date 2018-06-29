package com.example.ryanhsueh.uiplayground.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanhsueh.uiplayground.R;
import com.example.ryanhsueh.uiplayground.SimpleFragmentActivity;

public class FirstFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.btn_first_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SimpleFragmentActivity) getActivity()).updateFrameLayout();
            }
        });

    }
}
