package com.flagcamp.secondhands.ui.chat;

import android.content.Context;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flagcamp.secondhands.R;
import com.flagcamp.secondhands.model.Message;
import com.flagcamp.secondhands.model.User;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int MY_TEXT_MESSAGE = 0, THEIR_TEXT_MESSAGE = 1;

    private List<Message> messageList;
    private User user;

    public MessageAdapter(User user, List<Message> messageList) {
        this.messageList = messageList;
        this.user = user;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        if (message.getMessageUserId().equals(user.userId)) {
            return MY_TEXT_MESSAGE;
        }
        return THEIR_TEXT_MESSAGE;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case MY_TEXT_MESSAGE:
                SentMessageViewHolder holder1 = (SentMessageViewHolder) viewHolder;
                configureMyTextMessageViewHolder(holder1, position);
                break;
            case THEIR_TEXT_MESSAGE:
                ReceivedMessageViewHolder holder2 = (ReceivedMessageViewHolder) viewHolder;
                configureTheirTextMessageViewHolder(holder2, position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == MY_TEXT_MESSAGE) {
            View view = inflater.inflate(R.layout.item_message_sent, parent, false);
            viewHolder = new SentMessageViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_message_received, parent, false);
            viewHolder = new ReceivedMessageViewHolder(view);
        }
        return viewHolder;
    }

    private void configureMyTextMessageViewHolder(SentMessageViewHolder viewHolder, int position) {
        Message message = messageList.get(position);
        viewHolder.text.setText(message.getMessageText());
        viewHolder.time.setText(DateFormat.format("dd MMM  (h:mm a)", message.getMessageTime()));
    }

    private void configureTheirTextMessageViewHolder(ReceivedMessageViewHolder viewHolder, int position) {
        Message message = messageList.get(position);
        viewHolder.username.setText(message.getMessageUsername());
        Picasso.get().load(message.getMessagePhotoUrl()).into(viewHolder.imgProfile);
        viewHolder.text.setText(message.getMessageText());
        viewHolder.time.setText(DateFormat.format("dd MMM  (h:mm a)", message.getMessageTime()));
    }

}