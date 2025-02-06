package com.example.databaseapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    EditText etName, etEmail, etAge;
    TextView view;
    Button btnSubmit;
    ArrayList<UserData> users;
    UserAdapter userAdapter;
    UserData ud1;
    int id, position;
    String name, email;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAge = findViewById(R.id.etAge);
        view = findViewById(R.id.view);
        btnSubmit = findViewById(R.id.btnSubmit);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        age = intent.getIntExtra("age", 0);

        etName.setText(name);
        etEmail.setText(email);
        etAge.setText(""+age);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                users = new ArrayList<>();
                ud1 = new UserData();
                ud1.setId(id);
                ud1.setName(etName.getText().toString());
                ud1.setEmail(etEmail.getText().toString());
                ud1.setAge(Integer.parseInt(etAge.getText().toString()));
                databaseHelper.updateUser(ud1);
                Toast.makeText(UpdateActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}