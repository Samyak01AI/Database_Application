package com.example.databaseapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    OnUserClickOnListener onUserClickOnListener;
    ArrayList<UserData> users;
    public UserAdapter(ArrayList<UserData>users,OnUserClickOnListener onUserClickOnListener){
        this.onUserClickOnListener = onUserClickOnListener;
        this.users = users;
    }
    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_show_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tvName.setText("Name: "+users.get(i).getName());
        viewHolder.tvEmail.setText("Email: "+users.get(i).getEmail());
        viewHolder.tvAge.setText("Age: "+users.get(i).getAge());
        viewHolder.ivUser.setImageResource(users.get(i).getImgid());

        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.tvName.getContext(), "Name: "+users.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.tvEmail.getContext(), "Email: "+users.get(i).getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.tvAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.tvAge.getContext(), "Age: "+users.get(i).getAge(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserClickOnListener.onUserClick(users.get(i),i,"delete");
            }
        });
        viewHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserClickOnListener.onUserClick(users.get(i), i, "update");
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvEmail,tvAge;
        ImageView ivUser,update,delete;
        public ViewHolder(@NonNull View userView) {
            super(userView);
            tvName = userView.findViewById(R.id.tvName);
            tvEmail = userView.findViewById(R.id.tvEmail);
            tvAge = userView.findViewById(R.id.tvAge);
            ivUser = userView.findViewById(R.id.ivUser);
            update = userView.findViewById(R.id.update);
            delete = userView.findViewById(R.id.delete);
        }
    }
}