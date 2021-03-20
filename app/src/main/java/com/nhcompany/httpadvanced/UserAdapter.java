package com.nhcompany.httpadvanced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserDetails> userDetails;

    public UserAdapter(Context context, ArrayList<UserDetails> userDetails) {
        this.context = context;
        this.userDetails = userDetails;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserDetails userDetails1 = userDetails.get(position);
        holder.name.setText(userDetails1.getName());
        holder.likes.setText("Liked By :" + userDetails1.getLikes());
        Glide.with(context).load(userDetails1.getImageUrls()).into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return userDetails.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name , likes ;
        ImageView profileImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userName);
            likes = itemView.findViewById(R.id.likes);
            profileImage = itemView.findViewById(R.id.userPhoto);
        }
    }
}
