package com.example.lab3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class ChatRoomActivity extends AppCompatActivity {


    ListView chatList;
    EditText message;
    Button sendButton;
    Button receiveButton;
    List<Message> messageList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        chatList = findViewById(R.id.chat_room_LV);
        message = findViewById(R.id.bottom_text_box);
        sendButton = findViewById(R.id.sendButton);
        receiveButton = findViewById(R.id.receiveButton);
        messageList = new ArrayList<>();


        final ListAdapter chatListAdapter = new ListAdapter(messageList, getApplicationContext());

        chatList.setAdapter(chatListAdapter);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Message msg = new Message(message.getText().toString(), true);


                messageList.add(msg);
                message.setText("");
                chatListAdapter.notifyDataSetChanged();
            }
        });

        receiveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Message msg = new Message(message.getText().toString(), false);
                messageList.add(msg);
                message.setText("");
                chatListAdapter.notifyDataSetChanged();
            }
        });

    }
    private class ListAdapter extends BaseAdapter {
        private List<Message> msg;
        private Context ctx;
        private LayoutInflater inflater;

        public ListAdapter(List<Message> msg, Context ctx) {
            this.msg = msg;
            this.ctx = ctx;
            this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return msg.size();
        }

        @Override
        public Message getItem(int position) {
            return msg.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View newView = convertView;
            if (msg.get(position).isSend()){
                newView = inflater.inflate(R.layout.message_send, null);
            }else {
                newView = inflater.inflate(R.layout.message_receive, null);
            }
            TextView messageText = newView.findViewById(R.id.textViewMessage);
            messageText.setText(msg.get(position).getMessage());
            return newView;
        }
    }
}
