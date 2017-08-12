package com.chat.zenfong;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 12/08/17.
 */

public class RVChatAdapter extends RecyclerView.Adapter<RVChatAdapter.MyViewHolder> {
    private List<ChatModel> chats = new ArrayList<>();
    private Context context;

    public RVChatAdapter(List<ChatModel> chats, Context context) {
        this.chats = chats;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvUsername.setText(chats.get(position).getUsername() + " mengatakan : ");
        holder.tvContent.setText(chats.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUsername;
        private TextView tvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

}



