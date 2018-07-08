package com.example.ryanhsueh.uiplayground.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ryanhsueh.uiplayground.ITitleBarAction;
import com.example.ryanhsueh.uiplayground.MyTitleBar;
import com.example.ryanhsueh.uiplayground.R;
import com.example.ryanhsueh.uiplayground.Util;
import com.example.ryanhsueh.uiplayground.data.Friend;

import java.util.List;

public class ListViewActivity extends NoActionBarActivity implements ITitleBarAction {

    private ListView mListView;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        MyTitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setTitle("ListViewActivity");


        // initial ListView
        List<Friend> friends = Util.initFriends();
        mAdapter = new FriendAdapter(this, R.layout.friend_item, friends);
        ListView mListView = findViewById(R.id.list_friends);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this, "position : " + i, Toast.LENGTH_SHORT).show();
            }
        });

        View footer = LayoutInflater.from(this).inflate(R.layout.listview_footer, null, false);
        mListView.addFooterView(footer);
    }

    @Override
    public void onBackClicked() {

    }

    @Override
    public void onEditClicked() {
        if (mAdapter.getCount() > 0) {
            mAdapter.remove(mAdapter.getItem(0));
        }
    }

    public class FriendAdapter extends ArrayAdapter<Friend> {

        private int resourceId;

        public FriendAdapter(@NonNull Context context, int resource, @NonNull List<Friend> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;
            View view;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder)convertView.getTag();
            }

            Friend friend = getItem(position);
            viewHolder.textName.setText(friend.getName());
            viewHolder.textAge.setText(String.valueOf(friend.getAge()));

            Glide.with(getContext())
                    .load("http://goo.gl/gEgYUd")
                    .into(viewHolder.imgPhoto);

            return view;
        }

        class ViewHolder {
            TextView textName;
            TextView textAge;
            ImageView imgPhoto;

            public ViewHolder(View view) {
                this.textName = view.findViewById(R.id.text_name);
                this.textAge = view.findViewById(R.id.text_age);
                this.imgPhoto = view.findViewById(R.id.image_photo);
            }
        }

    }
}
