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

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {
    Context context;
    ArrayList<UserData> userData;

    public RetrofitAdapter(Context context, ArrayList<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserData userData1 = userData.get(position);
        holder.name.setText(userData1.getUser().getName());
        holder.likes.setText(userData1.getLikes());
        Glide.with(context).load(userData1.getUser().getProfileImage().getMedium()).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return userData.size();
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
