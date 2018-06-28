package com.example.ryanhsueh.uiplayground;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ryanhsueh.uiplayground.data.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mMsgList;

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout messageReceived;
        private LinearLayout messageSent;
        private TextView textReceived;
        private TextView textSent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageReceived = itemView.findViewById(R.id.message_received);
            messageSent = itemView.findViewById(R.id.message_sent);
            textReceived = itemView.findViewById(R.id.text_received);
            textSent = itemView.findViewById(R.id.text_sent);
        }
    }

    public MessageAdapter(List<Message> msgList) {
        mMsgList = msgList;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        MessageAdapter.ViewHolder holder = new MessageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder viewHolder, int position) {
        Message msg = mMsgList.get(position);
        if (msg.getType() == Message.Type.RECEIVED) {
            viewHolder.messageSent.setVisibility(View.GONE);
            viewHolder.messageReceived.setVisibility(View.VISIBLE);
            viewHolder.textReceived.setText(msg.getContent());
        } else {
            viewHolder.messageSent.setVisibility(View.VISIBLE);
            viewHolder.messageReceived.setVisibility(View.GONE);
            viewHolder.textSent.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
