package com.example.databaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnUserClickOnListener {

    RecyclerView recyclerView;
    EditText find;
    ArrayList<UserData> users;
    UserAdapter userAdapter;
    DatabaseHelper databaseHelper;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        find = findViewById(R.id.find);
        search = findViewById(R.id.search1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        databaseHelper = new DatabaseHelper(MainActivity.this);
        users = databaseHelper.getAllUsers();
        userAdapter = new UserAdapter(users,this);
        recyclerView.setAdapter(userAdapter);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserClick(null,0,"search");
            }
        });
    }
    @Override
    public void onUserClick(UserData userData, int position, String action) {
        if(action.equals("delete")) {
            int count = databaseHelper.deleteUser(userData.getId());
            if (count > 0) {
                users.remove(position);
                userAdapter.notifyDataSetChanged();
                Toast.makeText(this, "User "+userData.getName()+" Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        }
        else if(action.equals("update")){
            Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
            intent.putExtra("id",userData.getId());
            intent.putExtra("name",userData.getName());
            intent.putExtra("email",userData.getEmail());
            intent.putExtra("age",userData.getAge());
            intent.putExtra("position",position);
            startActivity(intent);
        }
        else if(action.equals("search")){
            if(!find.getText().toString().isEmpty()) {
                String name = find.getText().toString();
                ArrayList<UserData> userList = databaseHelper.searchUser(name);
                userAdapter = new UserAdapter(userList, this);
                recyclerView.setAdapter(userAdapter);
            }
            else{
                Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            }
        }
    }
}