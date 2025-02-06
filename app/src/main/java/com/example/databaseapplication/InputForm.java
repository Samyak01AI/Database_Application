package com.example.databaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InputForm extends AppCompatActivity {
    EditText etName, etEmail, etAge;
    TextView view;
    Button btnSubmit;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etAge = findViewById(R.id.etAge);
        view = findViewById(R.id.view);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String age = etAge.getText().toString();
                    int imgid = R.drawable.user;
                    UserData user = new UserData(name, email, age, imgid);
                    databaseHelper = new DatabaseHelper(InputForm.this);
                    long id = databaseHelper.insertUser(user);
                    Toast.makeText(InputForm.this, "User: " + id + " Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InputForm.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(InputForm.this, MainActivity.class);
                        startActivity(intent);
                }
            });

    }

    public boolean validateFields(){

        ArrayList<Boolean> list=new ArrayList<>();
        if(Validation.isValidName(etName.getText().toString())){
            list.add(true);
        }else{
            list.add(false);
        }
        if(Validation.isValidEmail(etEmail.getText().toString())){
            list.add(true);
        }else{
            list.add(false);
        }
        if(Validation.isValidAge(etAge.getText().toString())){
            list.add(true);
        }else{
            list.add(false);
        }
        return !list.contains(false);
    }
}