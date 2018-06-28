package com.example.ryanhsueh.uiplayground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.ryanhsueh.uiplayground.data.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatChatActivity extends AppCompatActivity {

    private MessageAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private List<Message> mMsgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_chat);


        mMsgList = initMessage();
        mAdapter = new MessageAdapter(mMsgList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView = findViewById(R.id.recycler_view_chat);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        final EditText editInput = findViewById(R.id.edit_input);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editInput.getText().toString();
                if (!"".equals(content)) {
                    Message msg = new Message(content, Message.Type.SENT);
                    mMsgList.add(msg);
                    mAdapter.notifyItemInserted(mMsgList.size() - 1);
                    mRecyclerView.scrollToPosition(mMsgList.size() - 1);
                    editInput.setText("");
                }
            }
        });


    }

    private List<Message> initMessage() {
        List<Message> msgList = new ArrayList<>();
        msgList.add(new Message("Hey! Are you there?", Message.Type.RECEIVED));
        msgList.add(new Message("Hi! What'up guy!", Message.Type.SENT));
        msgList.add(new Message("Do you want to hangout together?", Message.Type.RECEIVED));

        return msgList;
    }

}
