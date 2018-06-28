package com.example.ryanhsueh.uiplayground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.ryanhsueh.uiplayground.data.Friend;

import java.util.List;

public class WaterFallRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_fall_recycler);

        List<Friend> friends = Util.initFriendsWithRandomName(4);
        FriendRecyclerAdapter adapter = new FriendRecyclerAdapter(this, friends, R.layout.friend_item_2);
        adapter.setOnFriendItemClickListener(new FriendRecyclerAdapter.OnFriendItemClickListener() {
            @Override
            public void onItemClicked(int position) {

            }

            @Override
            public void onItemImageClicked(int position) {

            }
        });

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
