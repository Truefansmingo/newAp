package com.flagcamp.secondhands.ui.chat;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flagcamp.secondhands.R;
import com.flagcamp.secondhands.model.Message;

public class SentMessageViewHolder extends RecyclerView.ViewHolder {

    TextView text;
    TextView time;

    public SentMessageViewHolder(@NonNull View itemView) {
        super(itemView);

        text = itemView.findViewById(R.id.message_body_text);
        time = itemView.findViewById(R.id.message_time_text);
    }

    void bind(Message message) {
        text.setText(message.getMessageText());
        time.setText(DateFormat.format("dd MMM  (h:mm a)", message.getMessageTime()));
    }
}