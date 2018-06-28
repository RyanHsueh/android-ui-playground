package com.example.ryanhsueh.uiplayground;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ryanhsueh.uiplayground.data.Friend;

import java.util.List;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Friend> mFriendList;
    private int mResourceId;

    interface OnFriendItemClickListener {
        void onItemClicked(int position);
        void onItemImageClicked(int position);
    }
    private OnFriendItemClickListener mItemClickListener;

    public void setOnFriendItemClickListener(OnFriendItemClickListener listener) {
        mItemClickListener = listener;
    }

    public FriendRecyclerAdapter(Context context, List<Friend> friendsList, int resourceId) {
        mContext = context;
        mFriendList = friendsList;
        mResourceId = resourceId;

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView textName;
        TextView textAge;
        ImageView imgPhoto;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.rootView = view;
            this.textName = view.findViewById(R.id.text_name);
            this.textAge = view.findViewById(R.id.text_age);
            this.imgPhoto = view.findViewById(R.id.image_photo);
        }
    }

    @NonNull
    @Override
    public FriendRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        // initial ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(mResourceId, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Toast.makeText(mContext, "You clicked item > postion : " + position, Toast.LENGTH_SHORT).show();

                if (mItemClickListener != null) {
                    mItemClickListener.onItemClicked(position);
                }
            }
        });
        viewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Friend friend = mFriendList.get(position);
                Toast.makeText(mContext, "You clicked image > name : " + friend.getName(), Toast.LENGTH_SHORT).show();

                if (mItemClickListener != null) {
                    mItemClickListener.onItemImageClicked(position);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRecyclerAdapter.ViewHolder viewHolder, int position) {
        Friend friend = mFriendList.get(position);
        viewHolder.textName.setText(friend.getName());
        viewHolder.textAge.setText(String.valueOf(friend.getAge()));

        Glide.with(mContext)
                .load("http://goo.gl/gEgYUd")
                .into(viewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }
}
