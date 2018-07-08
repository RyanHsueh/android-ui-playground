package com.example.ryanhsueh.uiplayground.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ryanhsueh.uiplayground.FriendRecyclerAdapter;
import com.example.ryanhsueh.uiplayground.ITitleBarAction;
import com.example.ryanhsueh.uiplayground.R;
import com.example.ryanhsueh.uiplayground.Util;
import com.example.ryanhsueh.uiplayground.data.Friend;

import java.util.List;

public class RecycleViewActivity extends NoActionBarActivity implements ITitleBarAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        List<Friend> friends = Util.initFriends();
        FriendRecyclerAdapter adapter = new FriendRecyclerAdapter(this, friends, R.layout.friend_item);

        // Default is vertical scroll, you can change to horizontal
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackClicked() {

    }

    @Override
    public void onEditClicked() {

    }

}
